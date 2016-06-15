package com.android.test.a1shippro.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.test.a1shippro.Adapter.ItemAdpater;
import com.android.test.a1shippro.Model.Item;
import com.android.test.a1shippro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonHangMoiFragment extends Fragment {
    Spinner s1, s2;
    String[] ss1 = {"Đơn hàng mới nhất", "...", "...", "..."};
    String[] ss2 = {"Tất cả công việc", "...", "...", "..."};
    ListView listView;
    ItemAdpater adpater;
    List<Item> mListItem;
    public DonHangMoiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_don_hang_moi, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        s1 = (Spinner) view.findViewById(R.id.spinner);
        s2 = (Spinner) view.findViewById(R.id.spinner1);

        mListItem = new ArrayList<Item>();
        loadListItem();

        adpater = new ItemAdpater(getContext(),mListItem);
        listView.setAdapter(adpater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"Clicked "+ mListItem.get(position),Toast.LENGTH_LONG).show();
            }
        });

        final ArrayAdapter<String> as1 = new ArrayAdapter<String>
                (
                        getContext(),
                        R.layout.item_spinnet,
                        ss1
                );
        s1.setAdapter(as1);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), mListItem.get(position)+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final ArrayAdapter<String> as2 = new ArrayAdapter<String>
                (
                        getContext(),
                        R.layout.item_spinnet,
                        ss2
                );
        s2.setAdapter(as1);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), as1.getItemId(position)+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private void loadListItem() {
        for(int i =0; i< 100; i++){
            Item item = new Item(
                    "#12"+i,
                    "01/06/2016",
                    "11:00",
                    "Giao hàng",
                    "Q5, HCM -> Q.Bình Tân, HCM"
            );
            mListItem.add(item);
        }
    }
}
