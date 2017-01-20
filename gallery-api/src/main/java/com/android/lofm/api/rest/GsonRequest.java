package com.android.lofm.api.rest;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Volley adapter for JSON requests that will be parsed into Java objects by Gson.
 */
public class GsonRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    private Class<T> clazz;
    private Map<String, String> headers;
    private final Listener<T> listener;
    private Object requestBody;
    private static final String PROTOCOL_CHARSET = "utf-8";
    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers, Listener<T> listener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    /**
     * @param method        Http request type. ie. Method.GET, Method.POST etc.
     * @param url           The request url
     * @param requestBody
     * @param clazz
     * @param headers
     * @param listener
     * @param errorListener
     */
    public GsonRequest(int method, String url, Object requestBody, Class<T> clazz, Map<String, String> headers, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.requestBody = requestBody;
    }

    /**
     * Make a specified Http Request and return a parsed object from JSON.
     *
     * @param method        The Http Method type as specified by Request.Method
     * @param url           URL of the request to make
     * @param clazz         Relevant class object, for Gson's reflection
     * @param headers       The http headers
     * @param listener      Callback interface for delivering parsed responses
     * @param errorListener Callback interface for delivering error responses
     */
    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        /** Add required headers. */
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("Accept", "application/json");
        return headers;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    /**
     * @deprecated Use {@link #getBodyContentType()}.
     */
    @Override
    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    /**
     * @deprecated Use {@link #getBody()}.
     */
    @Override
    @Deprecated
    public byte[] getPostBody() {
        return getBody();
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
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        Response<T> netResponse = null;
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            netResponse = Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
        return netResponse;
    }
}
