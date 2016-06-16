package com.android.test.a1shippro.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.test.a1shippro.Adapter.RecyclerViewAdapter;
import com.android.test.a1shippro.Adapter.SpinnerAdapter;
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
    RecyclerView recyclerView;
    RecyclerViewAdapter adpater;
    SpinnerAdapter spinner1,spinner2;
    List<Item> mListItem;
    LinearLayoutManager linearLayoutManager;
    boolean loading = false;
    public DonHangMoiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_don_hang_moi, container, false);

        init(view);

        return view;
    }

    void init(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        s1 = (Spinner) view.findViewById(R.id.spinner);
        s2 = (Spinner) view.findViewById(R.id.spinner1);

        mListItem = new ArrayList<Item>();

        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        setupAdapter();
        spinner1 = new SpinnerAdapter(getContext(),ss1);
        s1.setAdapter(spinner1);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), spinner1.getItemId(position)+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2 = new SpinnerAdapter(getContext(),ss2);
        s2.setAdapter(spinner2);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), spinner2.getItemId(position)+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setupAdapter(){
        mListItem = new ArrayList<>();
        for(int i =0; i< 10; i++){
            Item item = new Item(
                    "#12"+i,
                    "01/06/2016",
                    "11:00",
                    "Giao hàng",
                    "Q5, HCM -> Q.Bình Tân, HCM"
            );
            mListItem.add(item);
        }
        adpater = new RecyclerViewAdapter(getContext(),mListItem);
        recyclerView.setAdapter(adpater);
        recyclerView.addOnScrollListener(onScrollListener());
    }

    private void loadListItem() {
        for(int i =0; i< 10; i++){
            Item item = new Item(
                    "#12"+i,
                    "01/06/2016",
                    "11:00",
                    "Giao hàng",
                    "Q5, HCM -> Q.Bình Tân, HCM"
            );
            mListItem.add(item);
        }
        adpater.notifyDataSetChanged();
        loading = false;
    }

    private RecyclerView.OnScrollListener onScrollListener(){

        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int count = recyclerView.getAdapter().getItemCount();
                if (newState == 0) {
//                    int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                    //position item cuối đang hiển thị mà bằng tổng số item lst thì load
                    if (linearLayoutManager.findLastVisibleItemPosition() >= count - 2&& !loading) {
                        recyclerView.setClickable(false);
                        loading = true;
                        loadListItem();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        };
    }
}
