package com.android.lofm.imagesdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.lofm.api.GalleryAPI;
import com.android.lofm.api.ui.ImageListener;
import com.android.lofm.imagesdemo.R;
import com.android.lofm.imagesdemo.adapter.ImageAdapter;
import com.android.lofm.imagesdemo.presenter.GridPresenter;

/**
 * Created by Omar F Martinez on 1/7/17.
 */
public class GridFragment extends Fragment implements ImageListener {

    public static final String TAG = GridFragment.class.getName();
    private static final String PACKAGE = "com.android.lofm.imagesdemo";
    private RecyclerView recyclerView;
    private GridLayoutManager glm;
    private GridPresenter gridPresenter;
    private Integer[] thumbIds;
    private ImageAdapter imageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_grid, container, false);
        gridPresenter = new GridPresenter(getContext());
        //gridPresenter.getImages("endpoint");
        thumbIds = gridPresenter.getImages();
        //Setup recycler view and adapter
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        imageAdapter = new ImageAdapter(getContext(), this);
        imageAdapter.setThumbIds(thumbIds);
        recyclerView.setAdapter(imageAdapter);
        glm = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(glm);
        return view;
    }

    public void onImageClicked(View view, int position) {
        ImageView imageView = (ImageView) view;
        int[] screenLocation = new int[2];
        imageView.getLocationOnScreen(screenLocation);
        Bundle bundle = new Bundle();
        bundle.putInt(PACKAGE + ".image", thumbIds[position]);
        GalleryAPI.getInstance(getActivity(), R.id.fragmentHolder).showExpandedImage(ImageDetailFragment.TAG, bundle, imageView);
    }

}
