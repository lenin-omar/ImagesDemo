package com.android.lofm.imagesdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.lofm.imagesdemo.R;
import com.android.lofm.imagesdemo.adapter.ImageAdapter;
import com.android.lofm.imagesdemo.util.FragmentUtil;

/**
 * Created by Omar F Martinez on 1/7/17.
 */
public class GridFragment extends Fragment {

    public static final String TAG = GridFragment.class.getName();
    private static final String PACKAGE = "com.android.lofm.imagesdemo";
    private GridView gridview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_grid, container, false);
        gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getContext()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ShowImageDetail(position, view);
            }
        });
        return view;
    }

    public void ShowImageDetail(int position, View view) {
        Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
        //TODO: Expand image and go to next screen
        ImageView imageView = (ImageView) ((ViewGroup) view).getChildAt(0);
        int[] screenLocation = new int[2];
        imageView.getLocationOnScreen(screenLocation);
//        Intent showDetailFragment = new Intent(getContext(), ImageDetailFragment.class);
        int orientation = getResources().getConfiguration().orientation;
//        showDetailFragment.
//                //Image information
//                putExtra(PACKAGE + ".orientation", orientation).
//                putExtra(PACKAGE + ".left", screenLocation[0]).
//                putExtra(PACKAGE + ".top", screenLocation[1]).
//                putExtra(PACKAGE + ".width", imageView.getWidth()).
//                putExtra(PACKAGE + ".height", imageView.getHeight());
//        startActivity(showDetailFragment);
        Bundle bundle = new Bundle();
        bundle.putInt(PACKAGE + ".orientation", orientation);
        bundle.putInt(PACKAGE + ".left", screenLocation[0]);
        bundle.putInt(PACKAGE + ".top", screenLocation[1]);
        bundle.putInt(PACKAGE + ".width", imageView.getWidth());
        bundle.putInt(PACKAGE + ".height", imageView.getHeight());
        FragmentUtil.addFragmentToContent(getActivity(), ImageDetailFragment.TAG, bundle);
        // Override transitions: we don't want the normal window animation in addition to our custom one
        getActivity().overridePendingTransition(0, 0);

    }

}
