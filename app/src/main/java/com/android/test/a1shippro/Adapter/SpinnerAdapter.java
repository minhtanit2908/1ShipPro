package com.android.test.a1shippro.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.s4you.FlyBeau.R;
import com.s4you.FlyBeau.model.Object.CompetitionDto;
import com.s4you.FlyBeau.utils.lazylist.BitmapBorderTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zanty on 25/04/2016.
 */
public class SpinnerAdapter extends BaseAdapter {
    Context context;
    List<CompetitionDto> mListCompetition = new ArrayList<>();

    public SpinnerAdapter(Context context, List<CompetitionDto> mListCompetition) {
        CompetitionDto comp = new CompetitionDto();
        if (mListCompetition.size() > 0) {
            this.mListCompetition.addAll(mListCompetition);
        }
        else {
            comp.setCompetitionId(-1);
            comp.setCompetitionName(context.getResources().getString(R.string.have_not_competition));
            this.mListCompetition.add(comp);
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        return mListCompetition.size();
    }

    @Override
    public Object getItem(int position) {
        return mListCompetition.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListCompetition.get(position).getCompetitionId();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false);
            view_item.textview = (TextView) convertView.findViewById(R.id.tv_custom_spinner);
            view_item.imageview = (ImageView) convertView.findViewById(R.id.iv_custom_spinner);
            convertView.setTag(view_item);
        } else
            view_item = (View_Item) convertView.getTag();
        view_item.textview.setText(mListCompetition.get(position).getCompetitionName());
        if (mListCompetition.get(position).getCompetitionId() == -1)
            view_item.imageview.setVisibility(View.GONE);
        else
            Picasso.with(context)
                    .load(mListCompetition.get(position).getCompetitionLogoUrl())
                    .transform(new BitmapBorderTransformation(0, 100, Color.BLUE))
                    .fit().centerCrop()
                    .error(R.drawable.ic_perm_identity_black_24dp)
                    .into(view_item.imageview);
        return convertView;
    }

    public static class View_Item {
        public ImageView imageview;
        public TextView textview;
    }

}
