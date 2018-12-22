package com.example.genius.take_outmanager.home_fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genius.take_outmanager.AllShopBean;
import com.example.genius.take_outmanager.R;
import com.example.genius.take_outmanager.Service;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopInfoAdapter extends RecyclerView.Adapter<ShopInfoAdapter.ViewHolder> {
    private static final String TAG = "ShopInfoAdapter";
    public static  List<AllShopBean.ShopArrBean> shopInfoList;
    public ShopInfoAdapter(List<AllShopBean.ShopArrBean> shopInfoList) {
        this.shopInfoList = shopInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_info_fragment_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AllShopBean.ShopArrBean  shop= shopInfoList.get(i);
        viewHolder.tv_shopName.setText(shop.getName());
        viewHolder.tv_address.setText(shop.getAddress());
        viewHolder.tv_grade.setText(shop.getGrade());
        viewHolder.tv_phone.setText(shop.getPhone());
        viewHolder.tv_sales.setText(shop.getScales());
        viewHolder.tv_time.setText(shop.getTime());
        viewHolder.bt_allow.setEnabled(true);
        viewHolder.bt_deny.setEnabled(true);
        viewHolder.bt_del.setEnabled(true);
        viewHolder.bt_black.setEnabled(true);
        viewHolder.bt_allow.setText("允许");
        viewHolder.bt_deny.setText("拒绝");
        int state = Integer.parseInt(shop.getState());
        if(state == 0){
            viewHolder.bt_allow.setEnabled(false);
            viewHolder.bt_deny.setEnabled(false);
            viewHolder.bt_deny.setText("已拒绝");
        }
        else if (state == 1){//正在请求

        }
        else if(state == 2){
            viewHolder.bt_allow.setEnabled(false);
            viewHolder.bt_deny.setEnabled(false);
            viewHolder.bt_allow.setText("已允许");
        }

    }

    @Override
    public int getItemCount() {
        return shopInfoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_shopName;
        TextView tv_address;
        TextView tv_phone;
        TextView tv_time;
        TextView tv_sales;
        TextView tv_grade;
        Button bt_allow;
        Button bt_deny;
        Button bt_del;
        Button bt_black;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tv_shopName = itemView.findViewById(R.id.shop_info_item_shop_name);
            tv_address = itemView.findViewById(R.id.shop_info_item_shop_address);
            tv_phone = itemView.findViewById(R.id.shop_info_item_shop_phone);
            tv_time = itemView.findViewById(R.id.shop_info_item_shop_time);
            tv_sales = itemView.findViewById(R.id.shop_info_item_shop_count);
            tv_grade = itemView.findViewById(R.id.shop_info_item_shop_grade);
            bt_allow = itemView.findViewById(R.id.shop_info_item_bt_allow);
            bt_deny = itemView.findViewById(R.id.shop_info_item_bt_deny);
            bt_del = itemView.findViewById(R.id.shop_info_item_bt_del);
            bt_black = itemView.findViewById(R.id.shop_info_item_bt_black_list);
            tv_shopName.setOnClickListener(this);
            tv_address.setOnClickListener(this);
            tv_phone.setOnClickListener(this);
            tv_time.setOnClickListener(this);
            tv_sales.setOnClickListener(this);
            tv_grade.setOnClickListener(this);
            bt_allow.setOnClickListener(this);
            bt_deny.setOnClickListener(this);
            bt_del.setOnClickListener(this);
            bt_black.setOnClickListener(this);
        }
        @Override
        public void onClick(final View v) {
            final EditText editText;
            AlertDialog.Builder inputDialog ;

            String sql;
            switch (v.getId()){
                case R.id.shop_info_item_shop_name:
                    editText = new EditText(v.getContext());
                    editText.setText(tv_shopName.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 商家 set C_name = '"+temp+"'where C_num = "+shopInfoList.get(getLayoutPosition()).getId();
                            shopInfoList.get(getLayoutPosition()).setName(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.shop_info_item_shop_address:
                    editText = new EditText(v.getContext());
                    editText.setText(tv_address.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 商家 set C_address = '"+temp+"'where C_num = "+shopInfoList.get(getLayoutPosition()).getId();
                            shopInfoList.get(getLayoutPosition()).setAddress(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.shop_info_item_shop_phone:
                    editText = new EditText(v.getContext());
                    editText.setText(tv_phone.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 商家 set C_phonenum = '"+temp+"'where C_num = "+shopInfoList.get(getLayoutPosition()).getId();
                            shopInfoList.get(getLayoutPosition()).setPhone(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.shop_info_item_shop_time:
                    editText = new EditText(v.getContext());
                    editText.setText(tv_time.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 商家 set C_time = '"+temp+"'where C_num = "+shopInfoList.get(getLayoutPosition()).getId();
                            shopInfoList.get(getLayoutPosition()).setTime(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.shop_info_item_shop_count:

                    break;
                case R.id.shop_info_item_shop_grade:

                    break;
                case R.id.shop_info_item_bt_del:
                    bt_del.setEnabled(false);
                    bt_allow.setEnabled(false);
                    bt_deny.setEnabled(false);
                    bt_black.setEnabled(false);
                    sql = "delete from  商家  where C_num = "+shopInfoList.get(getLayoutPosition()).getId();
                    update(sql,v.getContext());
                    break;
                case  R.id.shop_info_item_bt_black_list:
                    bt_del.setEnabled(false);
                    bt_allow.setEnabled(false);
                    bt_deny.setEnabled(false);
                    bt_black.setEnabled(false);
                    sql = "delete from  商家  where C_num = "+shopInfoList.get(getLayoutPosition()).getId()+";";
                    String temp = "insert into 商家黑名单(CB_phonenum,CB_shop_name) values('"+shopInfoList.get(getLayoutPosition()).getPhone()+"','"
                            +shopInfoList.get(getLayoutPosition()).getName()+"');";
                    Log.e(TAG, "onClick: "+sql+temp );
                    update(sql + temp,v.getContext());
                    shopInfoList.remove(getLayoutPosition());
                    break;
                case R.id.shop_info_item_bt_allow:
                    bt_allow.setEnabled(false);
                    bt_deny.setEnabled(false);
                    bt_allow.setText("已允许");
                    shopInfoList.get(getLayoutPosition()).setState("2");
                    sql = "update 商家 set C_state = 2  where C_num = "+shopInfoList.get(getLayoutPosition()).getId();
                    update(sql,v.getContext());
                    break;
                case R.id.shop_info_item_bt_deny:
                    bt_deny.setEnabled(false);
                    bt_allow.setEnabled(false);
                    bt_deny.setText("已拒绝");
                    shopInfoList.get(getLayoutPosition()).setState("0");
                    sql = "update 商家 set C_state = 0  where C_num = "+shopInfoList.get(getLayoutPosition()).getId();
                    update(sql,v.getContext());
                    break;
            }
        }

    }
     void  update(String sql, final Context context){
        /*final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("数据更新中，请稍后");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();*/
        final android.os.Handler handler = new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==2){
                    Looper.prepare();
                    String  temp = (String)msg.obj;
                    Toast.makeText(context,temp,Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        };
        String url = Service.updateInfo;
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder  requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        requestBody.addFormDataPart("sql",sql);
        final Request request = new Request.Builder().url(url).post(requestBody.build()).tag(context).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.e(TAG, "onResponse: "+response.body().string() );
                Message message = new Message();
                message.obj =  response.body().string();
                message.what = 2;
                handler.handleMessage(message);
            }
        });
    }

}
