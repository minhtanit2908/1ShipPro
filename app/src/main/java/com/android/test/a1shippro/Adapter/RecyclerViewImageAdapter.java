package com.android.test.a1shippro.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.test.a1shippro.Activity.UpdateDHActivity;
import com.android.test.a1shippro.R;

import java.util.List;

/**
 * Created by Zanty on 17/06/2016.
 */
public class RecyclerViewImageAdapter extends RecyclerView.Adapter<RecyclerViewImageAdapter.DataObjectHolder> {

    Context context;
    List<Bitmap> mList;
    boolean mAllEnabled = false;

    public RecyclerViewImageAdapter(Context context, List<Bitmap> mList) {
        this.context = context;
        this.mList = mList;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        ImageView iv,ivRemove;
        int iTag;
        public DataObjectHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageView);
            ivRemove = (ImageView) itemView.findViewById(R.id.ivRemove);
        }
    }

    @Override
    public RecyclerViewImageAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager_image,parent,false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewImageAdapter.DataObjectHolder holder, int position) {
        holder.iv.setImageBitmap(mList.get(position));
        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.iTag;
                ((UpdateDHActivity) context).removeItem(position);
            }
        });
        holder.iTag = position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
