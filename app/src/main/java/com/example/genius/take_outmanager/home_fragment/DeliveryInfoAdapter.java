package com.example.genius.take_outmanager.home_fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.genius.take_outmanager.AllDeliveyBean;
import com.example.genius.take_outmanager.R;

import java.util.List;

public class DeliveryInfoAdapter extends RecyclerView.Adapter<DeliveryInfoAdapter.ViewHolder> {
    List<AllDeliveyBean.DeliveryArrBean> list;

    public DeliveryInfoAdapter(List<AllDeliveyBean.DeliveryArrBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.delivery_info_fragment_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(list.get(i).getName());
        viewHolder.tv_phone.setText(list.get(i).getPhone());
        viewHolder.tv_grade.setText(list.get(i).getGrade());
        viewHolder.bt_black.setEnabled(true);
        viewHolder.bt_del.setEnabled(true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_name;
        TextView tv_phone;
        TextView tv_grade;
        Button bt_del;
        Button bt_black;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.delivery_info_item_name);
            tv_phone = itemView.findViewById(R.id.delivery_info_item_phone);
            tv_grade = itemView.findViewById(R.id.delivery_info_item_grade);
            bt_del = itemView.findViewById(R.id.delivery_info_item_bt_del);
            bt_black = itemView.findViewById(R.id.delivery_info_item_bt_black);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
