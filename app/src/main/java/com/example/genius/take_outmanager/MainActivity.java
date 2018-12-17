package com.example.genius.take_outmanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TabLayout mTabLayout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_user_centet:
                    mTextMessage.setText(R.string.title_user_center);
                    return true;
               /* case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        mTabLayout = findViewById(R.id.navigation_main_page_tab_layout);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        mTabLayout.addTab(mTabLayout.newTab().setText("商家信息"));
        mTabLayout.addTab(mTabLayout.newTab().setText("用户信息"));
        mTabLayout.addTab(mTabLayout.newTab().setText("骑手信息"));
        mTabLayout.addTab(mTabLayout.newTab().setText("订单信息"));

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
