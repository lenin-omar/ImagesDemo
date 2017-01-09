package com.android.lofm.imagesdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.lofm.imagesdemo.R;
import com.android.lofm.imagesdemo.adapter.ImageAdapter;
import com.android.lofm.imagesdemo.presenter.GridPresenter;
import com.android.lofm.imagesdemo.util.FragmentUtil;
import com.android.lofm.imagesdemo.util.ImageListener;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onImageClicked(View view, int position) {
        //TODO: Expand image and go to next screen
        ImageView imageView = (ImageView) view;
        Toast.makeText(getContext(), "position: " + position, Toast.LENGTH_SHORT).show();
        //
        int[] screenLocation = new int[2];
        imageView.getLocationOnScreen(screenLocation);
        Bundle bundle = new Bundle();
        bundle.putInt(PACKAGE + ".left", screenLocation[0]);
        bundle.putInt(PACKAGE + ".top", screenLocation[1]);
        bundle.putInt(PACKAGE + ".width", imageView.getWidth());
        bundle.putInt(PACKAGE + ".height", imageView.getHeight());
        bundle.putString(PACKAGE + ".image", "");
        //
        FragmentUtil.replaceFragmentToContent(getActivity(), ImageDetailFragment.TAG, bundle, imageView);
    }

}
