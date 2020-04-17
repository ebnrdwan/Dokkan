package com.engineering.dokkan.view.chating;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ChatingModel;
import com.engineering.dokkan.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatingFragment extends BaseFragment {

    EditText msgInputText;
    Button msgSendButton;
    RecyclerView msgRecyclerView;
    List<ChatingModel> msgList;
    ChatingAdapter adapter;

    public ChatingFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_chating;
    }

    @Override
    public void initializeViews(View view) {
        msgRecyclerView = view.findViewById(R.id.chat_recycler_view);
        msgInputText = view.findViewById(R.id.Enter_message);
        msgSendButton = view.findViewById(R.id.chat_send_msg);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        msgRecyclerView.setLayoutManager(linearLayoutManager);
        AddingMessagesToList();
        adapter = new ChatingAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);


    }


    @Override
    public void setListeners() {
        msgSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgContent = msgInputText.getText().toString();
                if (!TextUtils.isEmpty(msgContent)) {
                    // Add a new sent message to the list.
                    ChatingModel chatmodel = new ChatingModel(ChatingModel.MSG_TYPE_SENT, msgContent);
                    msgList.add(chatmodel);

                    int newMsgPosition = msgList.size() - 1;

                    // Notify recycler view insert one new data.
                    adapter.notifyItemInserted(newMsgPosition);

                    // Scroll RecyclerView to the last message.
                    msgRecyclerView.scrollToPosition(newMsgPosition);

                    // Empty the input edit text box.
                    msgInputText.setText("");
                }
            }
        });
    }


    private ArrayList<ChatingModel> AddingMessagesToList() {
        msgList = new ArrayList<ChatingModel>();
        ChatingModel message = new ChatingModel(ChatingModel.MSG_TYPE_RECEIVED, "Hello!");
        ChatingModel message2 = new ChatingModel(ChatingModel.MSG_TYPE_RECEIVED, "How are you? evreything ok?");
        msgList.add(message);
        msgList.add(message2);
        return (ArrayList<ChatingModel>) msgList;
    }
}
