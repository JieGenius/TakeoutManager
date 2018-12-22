package com.example.genius.take_outmanager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genius.take_outmanager.home_fragment.DeliveryBlackAdapter;
import com.example.genius.take_outmanager.home_fragment.DeliveryBlackFragment;
import com.example.genius.take_outmanager.home_fragment.DeliveryInfoFragment;
import com.example.genius.take_outmanager.home_fragment.OrderInfoFragment;
import com.example.genius.take_outmanager.home_fragment.ShopBlackFragment;
import com.example.genius.take_outmanager.home_fragment.ShopInfoFragment;
import com.example.genius.take_outmanager.home_fragment.UserBlackFragment;
import com.example.genius.take_outmanager.home_fragment.UserInfoFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private static ShopInfoFragment shopInfoFragment;
    private static UserInfoFragment userInfoFragment;
    private static DeliveryInfoFragment deliveryInfoFragment;
    private static OrderInfoFragment orderInfoFragment;
    private static ShopBlackFragment shopBlackFragment;
    private static UserBlackFragment userBlackFragment;
    private static DeliveryBlackFragment deliveryBlackFragment;
    private AllShopBean allShopBean;
    private AllUserBean allUserBean;
    private AllDeliveyBean allDeliveyBean;
    private AllOrderBean allOrderBean;
    private AllShopBlack allShopBlack;
    private AllDeliveryBlack allDeliveryBlack;
    private AllUserBlack allUserBlack;
    private Context context;
    private Handler handler;
    private final int UPDATESHOPDATA = 0x01;
    private final int UPDATEUSERDATA = 0x02;
    private final int UPDATEDELIVERYDATA = 0x03;
    private final int UPDATEORDERYDATA = 0x04;
    private final int UPDATESHOPBLACKDATA = 0x05;
    private final int UPDATEUSERBLACKYDATA = 0x06;
    private final int UPDATEDELIVERYBLACKYDATA = 0x07;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case UPDATESHOPDATA:
                        shopInfoFragment.addData(allShopBean.getShopArr());
                        break;
                    case UPDATEUSERDATA:
                        userInfoFragment.addData(allUserBean.getUserArr());
                        break;
                    case UPDATEDELIVERYDATA:
                        deliveryInfoFragment.addData(allDeliveyBean.getDeliveryArr());
                        break;
                    case UPDATEORDERYDATA:
                        orderInfoFragment.addData(allOrderBean.getOrderArr());
                        break;
                    case UPDATESHOPBLACKDATA:
                        shopBlackFragment.addData(allShopBlack.getShopBlackArr());
                        break;
                    case UPDATEUSERBLACKYDATA:
                        userBlackFragment.addData(allUserBlack.getUserBlackArr());
                        break;
                    case UPDATEDELIVERYBLACKYDATA:
                        deliveryBlackFragment.addData(allDeliveryBlack.getDeliveryBlackArr());

                }
            }
        };
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        context = container.getContext();
        TabLayout tabLayout = view.findViewById(R.id.home_fragment_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("商家信息"));
        tabLayout.addTab(tabLayout.newTab().setText("用户信息"));
        tabLayout.addTab(tabLayout.newTab().setText("骑手信息"));
        tabLayout.addTab(tabLayout.newTab().setText("订单信息"));
        tabLayout.addTab(tabLayout.newTab().setText("商家黑名单"));
        tabLayout.addTab(tabLayout.newTab().setText("用户黑名单"));
        tabLayout.addTab(tabLayout.newTab().setText("骑手黑名单"));
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        jumpFragment(shopInfoFragment);
                        break;
                    case 1:
                        jumpFragment(userInfoFragment);
                        break;
                    case 2:
                        jumpFragment(deliveryInfoFragment);
                        break;
                    case 3:
                        jumpFragment(orderInfoFragment);
                        break;
                    case 4:
                        jumpFragment(shopBlackFragment);
                        break;
                    case 5:
                        jumpFragment(userBlackFragment);
                        break;
                    case 6:
                        jumpFragment(deliveryBlackFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        shopInfoFragment = new ShopInfoFragment();
        userInfoFragment = new UserInfoFragment();
        deliveryInfoFragment = new DeliveryInfoFragment();
        orderInfoFragment = new OrderInfoFragment();
        shopBlackFragment = new ShopBlackFragment();
        userBlackFragment = new UserBlackFragment();
        deliveryBlackFragment = new DeliveryBlackFragment();
        runOnNewThread();
        jumpFragment(shopInfoFragment);
        return view;
    }
    private void jumpFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_fragment_container,fragment);
        fragmentTransaction.commit();

    }
    private void runOnNewThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getAllShop();
                getAllUser();
                getAllDelivery();
                getAllOrder();
                getShopBlack();
                getUserBlack();
                getDeliveryBlack();
            }
        });
        thread.start();
    }
    private void getAllShop(){
        String url = Service.getAllShopInfo;
        OkHttpClient client = new OkHttpClient();
        //MultipartBody.Builder  requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        Request request = new Request.Builder().url(url).tag(getActivity()).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allShopBean = new Gson().fromJson(response.body().string(),AllShopBean.class);
                handler.sendEmptyMessage(UPDATESHOPDATA);
                Log.e(TAG, "onResponse: "+"数据获取成功" );
            }
        });
    }
    private void getAllUser(){
        String url = Service.getAllUserInfo;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).tag(getActivity()).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allUserBean = new Gson().fromJson(response.body().string(),AllUserBean.class);
                handler.sendEmptyMessage(UPDATEUSERDATA);
            }
        });
    }
    private void getAllDelivery(){
        String url = Service.getAllDeliveryInfo;
        OkHttpClient client = new OkHttpClient();
        //MultipartBody.Builder  requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        Request request = new Request.Builder().url(url).tag(getActivity()).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allDeliveyBean = new Gson().fromJson(response.body().string(),AllDeliveyBean.class);
                handler.sendEmptyMessage(UPDATEDELIVERYDATA);
            }
        });
    }
    private void getAllOrder(){
        String url = Service.getAllOrderInfo;
        OkHttpClient client = new OkHttpClient();
        //MultipartBody.Builder  requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        Request request = new Request.Builder().url(url).tag(getActivity()).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allOrderBean = new Gson().fromJson(response.body().string(),AllOrderBean.class);
                handler.sendEmptyMessage(UPDATEORDERYDATA);
            }
        });
    }
    private void getShopBlack(){
        String url = Service.getShopBlack;
        OkHttpClient client = new OkHttpClient();
        //MultipartBody.Builder  requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        Request request = new Request.Builder().url(url).tag(getActivity()).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allShopBlack = new Gson().fromJson(response.body().string(),AllShopBlack.class);
                handler.sendEmptyMessage(UPDATESHOPBLACKDATA);
            }
        });
    }
    private void getUserBlack(){
        String url = Service.getUserBlack;
        OkHttpClient client = new OkHttpClient();
        //MultipartBody.Builder  requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        Request request = new Request.Builder().url(url).tag(getActivity()).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allUserBlack = new Gson().fromJson(response.body().string(),AllUserBlack.class);
                handler.sendEmptyMessage(UPDATEUSERBLACKYDATA);
            }
        });
    }
    private void getDeliveryBlack(){
        String url = Service.getDeliveryBlack;
        OkHttpClient client = new OkHttpClient();
        //MultipartBody.Builder  requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        Request request = new Request.Builder().url(url).tag(getActivity()).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allDeliveryBlack = new Gson().fromJson(response.body().string(),AllDeliveryBlack.class);
                handler.sendEmptyMessage(UPDATEDELIVERYBLACKYDATA);
            }
        });
    }
}
