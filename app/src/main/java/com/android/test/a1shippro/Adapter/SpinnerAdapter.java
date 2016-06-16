package com.android.test.a1shippro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.test.a1shippro.R;

/**
 * Created by Zanty on 25/04/2016.
 */
public class SpinnerAdapter extends BaseAdapter {
    Context context;
    String[] mList = {};

    public SpinnerAdapter(Context context, String[] mList) {
        this.mList =mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.length;
    }

    @Override
    public Object getItem(int position) {
        return mList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View_Item view_item;
        if (convertView == null) {
            view_item = new View_Item();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
            view_item.textview = (TextView) convertView.findViewById(R.id.tvSpinner);
            convertView.setTag(view_item);
        } else
            view_item = (View_Item) convertView.getTag();
        view_item.textview.setText(mList[position]);
        return convertView;
    }

    public static class View_Item {
        public TextView textview;
    }

}
