package com.android.lofm.imagesdemo.presenter;

import android.content.Context;

import com.android.lofm.imagesdemo.R;

/**
 * Created by Omar F Martinez on 1/8/17.
 */

public class GridPresenter {

    private Context context;

    public GridPresenter(Context context) {
        this.context = context;
    }

    //TODO: Get this from endpoint
    public Integer[] getImages() {
        Integer[] thumbIds = {R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7};
        return thumbIds;
    }

}
