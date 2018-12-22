package com.example.genius.take_outmanager;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.example.genius.take_outmanager.Service;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginTest {
    private static final String TAG = "LoginTest";
    static String getRandomFiveNum(){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        while (stringBuilder.length()<5){
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
    static void  canLogin(String tel, Context context,String num){
        Log.e(TAG, "canLogin: "+num );
        String url = "https://open.ucpaas.com/ol/sms/sendsms";
        OkHttpClient client = new OkHttpClient();

        Gson gson = new Gson();
        SendData sendData = new SendData(num+",60",tel);
        String json = gson.toJson(sendData);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        final Request request = new Request.Builder().url(url).post(requestBody).tag(context).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: 返回码"+response.code());
                Log.e(TAG, "onResponse: "+response.body().string() );
            }
        });
    }
}
