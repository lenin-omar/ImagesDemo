package com.android.lofm.imagesdemo.util;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;

import com.android.lofm.imagesdemo.R;
import com.android.lofm.imagesdemo.ui.DetailsTransition;

/**
 * Created by Omar F Martinez on 1/7/17.
 */

public class FragmentUtil {

    public static Fragment addFragmentToContent(FragmentActivity activity, String tag, Bundle extras) { //Used to add fragment to screen when app is launched
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = null;
        if (fm.findFragmentByTag(tag) == null) {
            fragment = Fragment.instantiate(activity, tag, extras);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragmentHolder, fragment, tag);
            ft.commit();
        }
        return fragment;
    }

    public static Fragment replaceFragmentToContent(FragmentActivity activity, String tag, Bundle extras, ImageView imageView) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().addSharedElement(imageView, "expandImage");
        Fragment fragment = Fragment.instantiate(activity, tag, extras);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.setSharedElementEnterTransition(new DetailsTransition());
            fragment.setEnterTransition(new Fade());
            fragment.setExitTransition(new Fade());
            fragment.setSharedElementReturnTransition(new DetailsTransition());
        }

        ft.replace(R.id.fragmentHolder, fragment, tag);
        ft.addToBackStack(null);
        ft.commit();
        return fragment;
    }

}
