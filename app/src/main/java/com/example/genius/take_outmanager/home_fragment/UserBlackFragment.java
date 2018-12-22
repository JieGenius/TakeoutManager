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
import com.example.genius.take_outmanager.AllUserBlack;
import com.example.genius.take_outmanager.R;
import java.util.ArrayList;
import java.util.List;
public class UserBlackFragment extends Fragment {
    List<AllUserBlack.UserBlackArrBean> list = new ArrayList<>();
    RecyclerView recyclerView ;
    public UserBlackAdapter userBlackAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_info_fragment,container,false);
        recyclerView = view.findViewById(R.id.shop_info_fragment_recycler_view);
        userBlackAdapter = new UserBlackAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userBlackAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(container.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(container.getContext(),R.drawable.home_fragment_divider_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;
    }
    public void addData(List<AllUserBlack.UserBlackArrBean> lists){
        this.list.clear();
        this.list.addAll(lists);
        if(userBlackAdapter!=null){
            userBlackAdapter.notifyDataSetChanged();
        }
    }
}
