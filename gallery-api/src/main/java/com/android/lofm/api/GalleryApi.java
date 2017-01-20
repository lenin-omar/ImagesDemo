package com.android.lofm.api;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.widget.ImageView;

import com.android.lofm.api.ui.DetailsTransition;

/**
 * Created by Omar F Martinez on 1/20/17.
 */

public class GalleryAPI {

    private static GalleryAPI galleryAPI = null;
    private int holderResource;
    private FragmentActivity activity;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private GalleryAPI(FragmentActivity activity, int holderResource) {
        this.activity = activity;
        this.holderResource = holderResource;
        fm = activity.getSupportFragmentManager();
    }

    public static GalleryAPI getInstance(FragmentActivity activity, int holderResource) {
        if (galleryAPI == null) {
            galleryAPI = new GalleryAPI(activity, holderResource);
        }
        return galleryAPI;
    }

    public void showGridGallery(String imageGridFragmentTag, Bundle extras) {
        Fragment imageGridFragment = null;
        if (fm.findFragmentByTag(imageGridFragmentTag) == null) {
            imageGridFragment = Fragment.instantiate(activity, imageGridFragmentTag, extras);
            ft = fm.beginTransaction();
            ft.add(holderResource, imageGridFragment, imageGridFragmentTag);
            ft.commit();
        }
    }

    public void showGridGallery(Fragment imageGridFragment, Bundle extras) {
        ft = fm.beginTransaction();
        ft.add(holderResource, imageGridFragment);
        ft.commit();
    }

    public void showExpandedImage(String expandedImageFragmentTag, Bundle extras, ImageView expandedImageView) {
        ft = fm.beginTransaction().addSharedElement(expandedImageView, "expandImage");
        Fragment expandedImageFragment = Fragment.instantiate(activity, expandedImageFragmentTag, extras);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            expandedImageFragment.setSharedElementEnterTransition(new DetailsTransition());
            expandedImageFragment.setEnterTransition(new Fade());
            expandedImageFragment.setExitTransition(new Fade());
            expandedImageFragment.setSharedElementReturnTransition(new DetailsTransition());
        }
        ft.replace(holderResource, expandedImageFragment, expandedImageFragmentTag);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void showExpandedImage(Fragment expandedImageFragment, Bundle extras, ImageView expandedImageView) {
        ft = fm.beginTransaction().addSharedElement(expandedImageView, "expandImage");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            expandedImageFragment.setSharedElementEnterTransition(new DetailsTransition());
            expandedImageFragment.setEnterTransition(new Fade());
            expandedImageFragment.setExitTransition(new Fade());
            expandedImageFragment.setSharedElementReturnTransition(new DetailsTransition());
        }
        ft.replace(holderResource, expandedImageFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}
