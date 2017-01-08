package com.android.lofm.imagesdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.lofm.imagesdemo.fragment.GridFragment;
import com.android.lofm.imagesdemo.util.FragmentUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: Create new method that receives activity, name of holder and source of images
        FragmentUtil.addFragmentToContent(this, GridFragment.TAG, null);
    }
}
