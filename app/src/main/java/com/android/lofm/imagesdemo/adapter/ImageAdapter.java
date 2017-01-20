package com.android.lofm.imagesdemo.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.lofm.api.ui.ImageListener;
import com.android.lofm.imagesdemo.R;

/**
 * Created by Omar F Martinez on 1/7/17.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private final ImageListener imageListener;
    private Context context;
    private Integer[] thumbIds;

    public ImageAdapter(Context context, ImageListener imageListener) {
        this.context = context;
        this.imageListener = imageListener;
    }

    public void setThumbIds(Integer[] thumbIds) {
        this.thumbIds = thumbIds;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        holder.initialImage.setImageResource(thumbIds[position]);
        ViewCompat.setTransitionName(holder.initialImage, String.valueOf(position) + "_image");
        holder.initialImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageListener.onImageClicked(holder.initialImage, position);
            }
        });
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return thumbIds.length;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        protected ImageView initialImage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            initialImage = (ImageView) itemView.findViewById(R.id.initialImage);
        }

    }
}