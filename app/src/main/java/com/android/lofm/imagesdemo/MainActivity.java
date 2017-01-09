package com.android.lofm.imagesdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.lofm.imagesdemo.util.GalleryUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GalleryUtil.showGallery(this, null);
    }
}
