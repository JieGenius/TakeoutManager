package com.example.genius.take_outmanager.home_fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.genius.take_outmanager.AllOrderBean;
import com.example.genius.take_outmanager.R;

import java.util.List;
public class OrderInfoAdapter extends RecyclerView.Adapter<OrderInfoAdapter.ViewHolder> {
    List<AllOrderBean.OrderArrBean> list;

    public OrderInfoAdapter(List<AllOrderBean.OrderArrBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_info_fragment_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.orderInfoItemId.setText(list.get(i).getId());
        viewHolder.orderInfoItemTime.setText(list.get(i).getTime());
        viewHolder.orderInfoItemDeliveryFee.setText(list.get(i).getDeliveryFee());
        viewHolder.orderInfoItemBoxFee.setText(list.get(i).getBoxFee());
        viewHolder.orderInfoItemSumFee.setText(list.get(i).getSumFee());
        viewHolder.orderInfoItemShopPhone.setText(list.get(i).getShopPhone());
        viewHolder.orderInfoItemDeliveyPhone.setText(list.get(i).getDeliveryPhone());
        viewHolder.orderInfoItemUserPhone.setText(list.get(i).getUserPhone());
        viewHolder.orderInfoItemComment.setText(list.get(i).getComment());
        viewHolder.orderInfoItemGrade.setText(list.get(i).getComment_grade());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    static public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderInfoItemId;
        TextView orderInfoItemTime;
        TextView orderInfoItemDeliveryFee;
        TextView orderInfoItemBoxFee;
        TextView orderInfoItemSumFee;
        TextView orderInfoItemShopPhone;
        TextView orderInfoItemDeliveyPhone;
        TextView orderInfoItemUserPhone;
        TextView orderInfoItemComment;
        TextView orderInfoItemGrade;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderInfoItemId = itemView.findViewById(R.id.order_info_item_id);
            orderInfoItemTime = itemView.findViewById(R.id.order_info_item_time);
            orderInfoItemDeliveryFee = itemView.findViewById(R.id.order_info_item_delivery_fee);
            orderInfoItemBoxFee = itemView.findViewById(R.id.order_info_item_box_fee);
            orderInfoItemSumFee = itemView.findViewById(R.id.order_info_item_sum_fee);
            orderInfoItemShopPhone = itemView.findViewById(R.id.order_info_item_shop_phone);
            orderInfoItemDeliveyPhone = itemView.findViewById(R.id.order_info_item_delivey_phone);
            orderInfoItemUserPhone = itemView.findViewById(R.id.order_info_item_user_phone);
            orderInfoItemComment = itemView.findViewById(R.id.order_info_item_comment);
            orderInfoItemGrade = itemView.findViewById(R.id.order_info_item_grade);
        }
    }
}
