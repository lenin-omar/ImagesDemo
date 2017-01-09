package com.android.lofm.imagesdemo.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.lofm.imagesdemo.R;
import com.android.lofm.imagesdemo.util.ImageListener;

/**
 * Created by Omar F Martinez on 1/7/17.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private final ImageListener imageListener;
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

//        holder.initialImage.setLayoutParams(new RecyclerView.LayoutParams(250, 250));
//        holder.initialImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        holder.initialImage.setPadding(10, 10, 10, 10);

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