package com.example.genius.take_outmanager.home_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genius.take_outmanager.AllDeliveyBean;
import com.example.genius.take_outmanager.AllOrderBean;
import com.example.genius.take_outmanager.R;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoFragment extends Fragment {

    List<AllOrderBean.OrderArrBean> list = new ArrayList<>();
    RecyclerView recyclerView ;
    public OrderInfoAdapter orderInfoAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_info_fragment,container,false);
        recyclerView = view.findViewById(R.id.shop_info_fragment_recycler_view);
        orderInfoAdapter = new OrderInfoAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(orderInfoAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(container.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(container.getContext(),R.drawable.home_fragment_divider_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;
    }

    public void addData(List<AllOrderBean.OrderArrBean> lists){
        this.list.clear();
        this.list.addAll(lists);
        if(orderInfoAdapter!=null){
            orderInfoAdapter.notifyDataSetChanged();
        }
    }
}
