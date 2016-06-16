package com.android.test.a1shippro.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.test.a1shippro.AbstractClass.BaseFragment;
import com.android.test.a1shippro.AbstractClass.HidingScrollListener;
import com.android.test.a1shippro.Adapter.RecyclerViewAdapter;
import com.android.test.a1shippro.Adapter.SpinnerAdapter;
import com.android.test.a1shippro.Model.Item;
import com.android.test.a1shippro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonHangMoiFragment extends BaseFragment {
    Spinner s1, s2;
    String[] ss1 = {"Đơn hàng mới nhất", "...", "...", "..."};
    String[] ss2 = {"Tất cả công việc", "...", "...", "..."};
    List<Item> mListItem;

    CheckBox cbAll;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout btnReceive, btnDismiss, btnDismiss1, btnUndo, lnDefault, lnConfirm;

    RecyclerViewAdapter adpater;
    SpinnerAdapter spinner1, spinner2;

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

    void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        s1 = (Spinner) view.findViewById(R.id.spinner);
        s2 = (Spinner) view.findViewById(R.id.spinner1);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        cbAll = (CheckBox) view.findViewById(R.id.cbAll1);

        initButton(view);
        setupSpinner();
        setupRecyclerView();
        setupSwipeRefresh();
        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAll(isChecked);
            }
        });
    }

    void initButton(View view) {
        btnReceive = (LinearLayout) view.findViewById(R.id.btnReceive);
        btnDismiss = (LinearLayout) view.findViewById(R.id.btnDismiss);
        lnDefault = (LinearLayout) view.findViewById(R.id.lnDefault);
        lnConfirm = (LinearLayout) view.findViewById(R.id.lnConfirm);
        btnUndo = (LinearLayout) view.findViewById(R.id.btnUndo);
        btnDismiss1 = (LinearLayout) view.findViewById(R.id.btnDismiss1);

        //First show default linear default
        lnDefault.setVisibility(View.VISIBLE);
        lnConfirm.setVisibility(View.GONE);
        //set on click for button

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCB()){
                    //show linear confirm after delete
                    lnDefault.setVisibility(View.GONE);
                    lnConfirm.setVisibility(View.VISIBLE);
                    adpater.setAllItemsEnabled(false);
                    recyclerView.setEnabled(false);
                    recyclerView.setClickable(false);
                }
            }
        });
        btnDismiss1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //before confirm delete
                removeItemList();
                lnDefault.setVisibility(View.VISIBLE);
                lnConfirm.setVisibility(View.GONE);
                cbAll.setChecked(false);
                adpater.setAllItemsEnabled(true);
            }
        });
        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receiveDonHang();
            }
        });
        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnDefault.setVisibility(View.VISIBLE);
                lnConfirm.setVisibility(View.GONE);
                adpater.setAllItemsEnabled(true);
            }
        });
    }

    boolean checkCB() {
        for (int i = 0; i< mListItem.size();i++)
            if (mListItem.get(i).isCheck())
                return true;
        return false;
    }

    private void receiveDonHang() {
    }

    void removeItemList() {
        List<Item> mListRespone = new ArrayList<>();
        for (int i = 0; i < mListItem.size(); i++)
            if (!mListItem.get(i).isCheck()) {
                mListRespone.add(mListItem.get(i));
            }
        mListItem.clear();
        mListItem.addAll(mListRespone);
        adpater.notifyDataSetChanged();
    }

    public void checkAll(boolean check) {
        if (check) {
            for (int i = 0; i < mListItem.size(); i++)
                mListItem.get(i).setCheck(true);
            adpater.notifyDataSetChanged();
        } else {
            for (int i = 0; i < mListItem.size(); i++)
                mListItem.get(i).setCheck(false);
            adpater.notifyDataSetChanged();
        }
    }

    @Override
    public void longClickItem() {
    }

    public void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mListItem = new ArrayList<Item>();
        mListItem = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item(
                    "#12" + i,
                    "01/06/2016",
                    "11:00",
                    "Giao hàng",
                    "Q5, HCM -> Q.Bình Tân, HCM",
                    false
            );
            mListItem.add(item);
        }
        adpater = new RecyclerViewAdapter(getContext(), mListItem, this);
        recyclerView.setAdapter(adpater);
        recyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onUp() {
//                ((MainActivity)getActivity()).getToolbar().setVisibility(View.GONE);
            }

            @Override
            public void onDown() {
//                ((MainActivity)getActivity()).getToolbar().setVisibility(View.VISIBLE);
            }

            @Override
            public void loadMore() {
                if (!loading) {
                    loadListItem();
                    loading = false;
                }
            }
        });
    }


    public void setupSpinner() {
        /*spinner1 = new SpinnerAdapter(getContext(),ss1);
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
        });*/
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_item, //set layout choose
                ss1
        );
        //set layout for spinner
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        s1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_item,
                ss2
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        s2.setAdapter(adapter2);
    }

    void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshSwipe();
            }
        });
    }

    private void refreshSwipe() {
        mListItem.clear();
        recyclerView.setEnabled(false);
        loadListItem();

    }

    public void finishRefresh() {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        recyclerView.setEnabled(true);
    }

    private void loadListItem() {
        for (int i = 0; i < 10; i++) {
            Item item = new Item(
                    "#12" + i,
                    "01/06/2016",
                    "11:00",
                    "Giao hàng",
                    "Q5, HCM -> Q.Bình Tân, HCM",
                    false
            );
            mListItem.add(item);
        }
        adpater.notifyDataSetChanged();
        loading = false;
        finishRefresh();
    }
}
