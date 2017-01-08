package com.android.lofm.imagesdemo.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.lofm.imagesdemo.R;

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
//    public static Fragment replaceFragmentToContent(FragmentActivity activity, String tag, Bundle extras, boolean isSecondaryNavigation) {
//        FragmentManager fm = activity.getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        Fragment fragment = Fragment.instantiate(activity, tag, extras);
//        if (isSecondaryNavigation) {    //Fragment is not in nav drawer menu
//            ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
//            ft.replace(R.id.fragmentHolder, fragment, tag);
//            ft.addToBackStack(null);
//            ft.commit();
//        } else {
//            if (fm.findFragmentByTag(tag) == null) {
//                ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
//                ft.replace(R.id.fragmentHolder, fragment, tag);
//                ft.addToBackStack(tag);
//                ft.commit();
//            } else if (!fm.findFragmentByTag(tag).isVisible()) {
//                ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
//                ft.replace(R.id.fragmentHolder, fragment, tag);
//                ft.addToBackStack(tag);
//                ft.commit();
//            }
//        }
//        return fragment;
//    }

}
