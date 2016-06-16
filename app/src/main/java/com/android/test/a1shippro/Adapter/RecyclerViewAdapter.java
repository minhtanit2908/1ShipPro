package com.android.test.a1shippro.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.test.a1shippro.AbstractClass.BaseFragment;
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
    BaseFragment fragment;
    boolean mAllEnabled = false;

    public RecyclerViewAdapter(Context context, List<Item> mList, BaseFragment fragment) {
        this.context = context;
        this.mList = mList;
        this.fragment = fragment;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDay, tvTime, tvCongViec, tvDiaDiem;
        int iTag;
        LinearLayout main;
        CheckBox checkBox;
        public DataObjectHolder(View itemView) {
            super(itemView);
            main = (LinearLayout) itemView.findViewById(R.id.mainList);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDay = (TextView) itemView.findViewById(R.id.tvDay);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvCongViec = (TextView) itemView.findViewById(R.id.tvCongViec);
            tvDiaDiem = (TextView) itemView.findViewById(R.id.tvDiaDiem);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }

    @Override
    public RecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview,parent,false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.DataObjectHolder holder, final int position) {
        homeItem = mList.get(position);
        holder.tvTitle.setText(homeItem.getTitle());
        holder.tvDay.setText(homeItem.getDay());
        holder.tvTime.setText(homeItem.getTime());
        holder.tvCongViec.setText(homeItem.getCongviec());
        holder.tvDiaDiem.setText(homeItem.getDiadiem());
        holder.iTag = position;
        if(homeItem.isCheck())
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);
        holder.main.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fragment.longClickItem();
                return false;
            }
        });
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,mList.get(position).getTitle(),Toast.LENGTH_LONG).show();
            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mList.get(holder.iTag).setCheck(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onViewAttachedToWindow(DataObjectHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.itemView.setEnabled(isAllItemsEnabled());
    }

    public boolean isAllItemsEnabled(){ return mAllEnabled; }


    public boolean getItemEnabled(int position){
        return true;
    }
    public void setAllItemsEnabled(boolean enable){
        mAllEnabled = enable;
        notifyItemRangeChanged(0, getItemCount());
    }
}
