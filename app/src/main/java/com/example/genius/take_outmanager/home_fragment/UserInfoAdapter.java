package com.example.genius.take_outmanager.home_fragment;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genius.take_outmanager.AllUserBean;
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

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.ViewHolder> {
    private static final String TAG = "UserInfoAdapter";
    List<AllUserBean.UserArrBean> list;
    public UserInfoAdapter(List<AllUserBean.UserArrBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_info_fragment_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(list.get(i).getName());
        viewHolder.tv_address.setText(list.get(i).getAddress());
        viewHolder.tv_phone.setText(list.get(i).getPhone());
        viewHolder.bt_black.setEnabled(true);
        viewHolder.bt_del.setEnabled(true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_name;
        TextView tv_phone;
        TextView tv_address;
        Button bt_del;
        Button bt_black;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.user_info_item_name);
            tv_phone = itemView.findViewById(R.id.user_info_item_phone);
            tv_address = itemView.findViewById(R.id.user_info_item_address);
            bt_del = itemView.findViewById(R.id.user_info_item_bt_del);
            bt_black = itemView.findViewById(R.id.user_info_item_bt_black);
        }

        @Override
        public void onClick(View v) {
            String sql ;
            switch (v.getId()){
                case R.id.user_info_item_name:

                    break;
                case R.id.user_info_item_phone:

                    break;
                case R.id.user_info_item_address:

                    break;
                case R.id.user_info_item_bt_del:
                    sql = "delete from 用户 where U_num = "+list.get(getLayoutPosition()).getId();
                    update(sql,v.getContext());
                    break;

                case R.id.user_info_item_bt_black:
                    sql = "delete from 用户 where U_num = "+list.get(getLayoutPosition()).getId()+";";
                    String temp = "insert into 用户黑名单(UB_phone,UB_name) values ('"+list.get(getLayoutPosition()).getPhone()+"','"
                            +list.get(getLayoutPosition()).getName()+");";
                    update(sql,v.getContext());
                    break;
            }
        }
    }
    static void update(String sql, final Context context){
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
