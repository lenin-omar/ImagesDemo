package com.android.lofm.imagesdemo.presenter;

import android.content.Context;

import com.android.lofm.api.rest.CustomRequest;
import com.android.lofm.api.rest.VolleySingleton;
import com.android.lofm.imagesdemo.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omar F Martinez on 1/8/17.
 */

public class GridPresenter implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private RequestQueue requestQueue;

    public GridPresenter(Context context) {
        this.context = context;
    }

    public Integer[] getImages() {
        Integer[] thumbIds = {R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7};
        return thumbIds;
    }

    public void getImages(String url) {
        Map<String, String> headers = new HashMap<String, String>();
        CustomRequest customRequest = new CustomRequest(Request.Method.GET, url, headers, this, this);
        requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        requestQueue.add(customRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println("Response: " + response);
    }
}
