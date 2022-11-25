package com.example.aflo;

import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class JsonObjectWithHeaderRequest extends JsonObjectRequest {
    public JsonObjectWithHeaderRequest(String url, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public JsonObjectWithHeaderRequest(int method, String url, @Nullable JSONObject jsonRequest, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        Log.d("NetworkResponse", response.toString());
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(
                    response.headers, PROTOCOL_CHARSET));
            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONObject responseHeaders = response.headers != null ?
                    new JSONObject(response.headers) : null;
            jsonResponse.put("headers", responseHeaders);
            return Response.success(jsonResponse, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException | JSONException e) {
            return Response.error(new ParseError(e));
        }
    }
}
