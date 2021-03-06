package com.example.genius.take_outmanager.home_fragment;

import android.app.AlertDialog;
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

import com.example.genius.take_outmanager.AllDeliveyBean;
import com.example.genius.take_outmanager.R;
import com.example.genius.take_outmanager.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DeliveryInfoAdapter extends RecyclerView.Adapter<DeliveryInfoAdapter.ViewHolder> {
    private static final String TAG = "DeliveryInfoAdapter";
    static List<AllDeliveyBean.DeliveryArrBean> list;

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
            tv_name.setOnClickListener(this);
            tv_phone.setOnClickListener(this);
            tv_grade.setOnClickListener(this);
            bt_del.setOnClickListener(this);
            bt_black.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            final EditText editText;
            AlertDialog.Builder inputDialog;
            switch (v.getId()) {
                case R.id.delivery_info_item_name:
                    editText = new EditText(v.getContext());
                    editText.setText(tv_name.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 骑手 set D_name = '"+temp+"'where D_num = "+list.get(getLayoutPosition()).getId();
                            list.get(getLayoutPosition()).setName(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.delivery_info_item_phone:
                    editText = new EditText(v.getContext());
                    editText.setText(tv_phone.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 骑手 set D_phonenum = '"+temp+"'where D_num = "+list.get(getLayoutPosition()).getId();
                            list.get(getLayoutPosition()).setPhone(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.delivery_info_item_grade:
                    editText = new EditText(v.getContext());
                    editText.setText(tv_grade.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 骑手 set D_grade = '"+temp+"'where D_grade = "+list.get(getLayoutPosition()).getId();
                            list.get(getLayoutPosition()).setGrade(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.delivery_info_item_bt_del:
                    String sql = "delete from 骑手 where D_num = "+list.get(getLayoutPosition()).getId()+";";
                    update(sql,v.getContext());
                    list.remove(getLayoutPosition());
                    break;
                case R.id.delivery_info_item_bt_black:
                    String temp = "delete from 骑手 where D_num = "+list.get(getLayoutPosition()).getId()+";";
                    String temp1 =  "insert into 骑手黑名单(DB_phone,DB_name) values ('"+list.get(getLayoutPosition()).getPhone()+"','"
                            +list.get(getLayoutPosition()).getName()+");";;
                    update(temp+temp1,v.getContext());
                    list.remove(getLayoutPosition());
                    break;
            }
        }
    }
    static void update(String sql, final Context context) {
        /*final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("数据更新中，请稍后");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();*/
        final android.os.Handler handler = new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 2) {
                    Looper.prepare();
                    String temp = (String) msg.obj;
                    Toast.makeText(context, temp, Toast.LENGTH_SHORT).show();

                    Looper.loop();
                }
            }
        };
        String url = Service.updateInfo;
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        requestBody.addFormDataPart("sql", sql);
        final Request request = new Request.Builder().url(url).post(requestBody.build()).tag(context).build();
        client.newBuilder().readTimeout(10, TimeUnit.MINUTES).connectTimeout(10, TimeUnit.MINUTES).writeTimeout(10, TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.e(TAG, "onResponse: "+response.body().string() );
                Message message = new Message();
                message.obj = response.body().string();
                message.what = 2;
                handler.handleMessage(message);
            }
        });
    }
}
