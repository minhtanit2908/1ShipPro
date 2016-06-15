package com.android.test.a1shippro.Adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.test.a1shippro.Model.Item;
import com.android.test.a1shippro.R;

import java.util.List;


public class ItemAdpater extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater = null;
    Item homeItem;
    List<Item> lstHome;
    DisplayMetrics metrics;
    int deviceWidth;

    public ItemAdpater(Context context, List<Item> lstHome) {
        //activity = a;
        this.context = context;
        this.lstHome = lstHome;
        metrics = context.getResources().getDisplayMetrics();
        deviceWidth = metrics.widthPixels;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lstHome.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        try {
            final Holder holder;


            homeItem = lstHome.get(position);
            if (convertView == null) {
                holder = new Holder();

                inflater = (LayoutInflater) this.context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_listview, null);

                holder.main = (LinearLayout) convertView.findViewById(R.id.mainList);
                holder.sub = (LinearLayout) convertView.findViewById(R.id.subList);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                holder.tvDay = (TextView) convertView.findViewById(R.id.tvDay);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
                holder.tvCongViec = (TextView) convertView.findViewById(R.id.tvCongViec);
                holder.tvDiaDiem = (TextView) convertView.findViewById(R.id.tvDiaDiem);

                convertView.setTag(holder);

            } else {
                holder = (Holder) convertView.getTag();
            }
            if (position == lstHome.size()-1) {
                holder.main.setVisibility(View.GONE);
                holder.sub.setVisibility(View.VISIBLE);
                holder.tvTitle.setText(homeItem.getTitle());
                holder.tvDay.setText(homeItem.getDay());
                holder.tvTime.setText(homeItem.getTime());
                holder.tvCongViec.setText(homeItem.getCongviec());
                holder.tvDiaDiem.setText(homeItem.getDiadiem());
                holder.iTag = position;
            } else {
                holder.main.setVisibility(View.VISIBLE);
                holder.sub.setVisibility(View.GONE);
                holder.tvTitle.setText(homeItem.getTitle());
                holder.tvDay.setText(homeItem.getDay());
                holder.tvTime.setText(homeItem.getTime());
                holder.tvCongViec.setText(homeItem.getCongviec());
                holder.tvDiaDiem.setText(homeItem.getDiadiem());

                holder.iTag = position;
            }
        } catch (Exception e) {
        }

        return convertView;
    }

    class Holder {
        TextView tvTitle, tvDay, tvTime, tvCongViec, tvDiaDiem;
        int iTag;
        LinearLayout main, sub;

        public Holder() {

        }
    }
}//