package com.engineering.dokkan;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.recycler);

        ArrayList <ChatModel> chatList=new ArrayList<>();
        chatList.add(new ChatModel(R.drawable.icon1,"Shop Name","Is the price ok?","11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon2,"Shop Name","Is the price ok?","11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon3,"Shop Name","Is the price ok?","11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon4,"Shop Name","Is the price ok?","11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon1,"Shop Name","Is the price ok?","11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon2,"Shop Name","Is the price ok?","11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon4,"Shop Name","Is the price ok?","11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon3,"Shop Name","Is the price ok?","11:05 Am"));
        MessageAdapter adapter= new MessageAdapter(chatList);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        DividerItemDecoration dv ;
        dv=new DividerItemDecoration(rv.getContext(),((LinearLayoutManager) lm).getOrientation());
        rv.addItemDecoration(dv);


    }
}
