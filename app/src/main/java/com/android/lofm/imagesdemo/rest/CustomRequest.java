package com.android.lofm.imagesdemo.rest;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omar F Martinez on 1/9/17.
 */

public class CustomRequest extends Request<JSONObject> {

    private static final String PROTOCOL_CHARSET = "utf-8";
    //Content type for request.
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private final Gson gson = new Gson();
    private final Response.Listener<JSONObject> listener;
    private Map<String, String> headers;
    private Object requestBody;

    /**
     * Make a specified Http Request and return a parsed object from JSON.
     *
     * @param method        The Http Method type as specified by Request.Method
     * @param url           URL of the request to make
     * @param headers       The http headers
     * @param listener      Callback interface for delivering parsed responses
     * @param errorListener Callback interface for delivering error responses
     */
    public CustomRequest(int method, String url, Map<String, String> headers, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        /** Add required headers. */
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put("Accept", "application/json");
        return headers;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() {
        try {
            String jsonObject = gson.toJson(requestBody);
            return requestBody == null ? null : jsonObject.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        Response<JSONObject> netResponse = null;
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            netResponse = Response.success(new JSONObject(json), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return netResponse;
    }

}
