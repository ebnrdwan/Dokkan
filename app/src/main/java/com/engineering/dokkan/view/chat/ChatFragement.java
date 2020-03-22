package com.engineering.dokkan.view.chat;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ChatModel;
import com.engineering.dokkan.view.base.BaseFragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragement extends BaseFragment {
    RecyclerView rv;

    public ChatFragement() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat_fragement;
    }

    @Override
    public void initializeViews(View view) {
        rv = view.findViewById(R.id.recycler);

        ArrayList<ChatModel> chatList = new ArrayList<>();
        chatList.add(new ChatModel(R.drawable.icon4, "Shop Name", "Is the price ok for you?", "11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon5, "Shop Name", "Is the price ok for you?", "11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon3, "Shop Name", "Is the price ok for you?", "11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon7, "Shop Name", "Is the price ok for you?", "11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon6, "Shop Name", "Is the price ok for you?", "11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon2, "Shop Name", "Is the price ok for you?", "11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon5, "Shop Name", "Is the price ok for you?", "11:05 Am"));
        chatList.add(new ChatModel(R.drawable.icon8, "Shop Name", "Is the price ok for you?", "11:05 Am"));
        chatAdapter adapter = new chatAdapter(chatList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        DividerItemDecoration dv;
        dv = new DividerItemDecoration(rv.getContext(), ((LinearLayoutManager) lm).getOrientation());
        rv.addItemDecoration(dv);
    }


    @Override
    public void setListeners() {

    }



}
