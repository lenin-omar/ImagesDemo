package com.android.lofm.imagesdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.android.lofm.imagesdemo.fragment.GridFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Move this to a different class (custom API/SDK)
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = Fragment.instantiate(this, GridFragment.TAG);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentHolder, fragment, GridFragment.TAG);
        ft.commit();
    }
}
