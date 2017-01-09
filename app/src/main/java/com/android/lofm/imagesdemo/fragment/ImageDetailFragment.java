package com.android.lofm.imagesdemo.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.lofm.imagesdemo.R;

public class ImageDetailFragment extends Fragment {

    public static final String TAG = ImageDetailFragment.class.getName();
    private static final String PACKAGE = "com.android.lofm.imagesdemo";
    private ImageView imageViewDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        imageViewDetail = (ImageView) view.findViewById(R.id.imageViewDetail);
        //Retrieve the data to display
        Bundle bundle = getArguments();
        imageViewDetail.setImageResource(bundle.getInt(PACKAGE + ".image", R.mipmap.ic_launcher));
        return view;
    }

}
