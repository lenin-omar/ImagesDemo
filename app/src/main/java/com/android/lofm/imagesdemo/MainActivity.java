package com.android.lofm.imagesdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.lofm.api.GalleryAPI;
import com.android.lofm.imagesdemo.fragment.GridFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GalleryAPI.getInstance(this, R.id.fragmentHolder).showGridGallery(GridFragment.TAG, null);
    }
}
