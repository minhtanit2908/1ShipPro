package com.android.test.a1shippro.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.test.a1shippro.Model.Item;
import com.android.test.a1shippro.R;

import java.util.List;

/**
 * Created by Zanty on 15/06/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataObjectHolder> {

    Context context;
    List<Item> mList;
    Item homeItem;

    public RecyclerViewAdapter(Context context, List<Item> mList) {
        this.context = context;
        this.mList = mList;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDay, tvTime, tvCongViec, tvDiaDiem;
        int iTag;
        LinearLayout main;
        public DataObjectHolder(View itemView) {
            super(itemView);
            main = (LinearLayout) itemView.findViewById(R.id.mainList);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDay = (TextView) itemView.findViewById(R.id.tvDay);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvCongViec = (TextView) itemView.findViewById(R.id.tvCongViec);
            tvDiaDiem = (TextView) itemView.findViewById(R.id.tvDiaDiem);
        }
    }

    @Override
    public RecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview,parent,false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.DataObjectHolder holder, int position) {
        homeItem = mList.get(position);
        holder.tvTitle.setText(homeItem.getTitle());
        holder.tvDay.setText(homeItem.getDay());
        holder.tvTime.setText(homeItem.getTime());
        holder.tvCongViec.setText(homeItem.getCongviec());
        holder.tvDiaDiem.setText(homeItem.getDiadiem());

        holder.iTag = position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
