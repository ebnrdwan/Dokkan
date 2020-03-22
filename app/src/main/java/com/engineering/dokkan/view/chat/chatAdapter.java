package com.engineering.dokkan.view.chat;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.ChatModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class chatAdapter extends RecyclerView.Adapter<chatAdapter.ChatViewHolder> {
    ArrayList<ChatModel> chatList;

    public chatAdapter(ArrayList<ChatModel> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_of_recycler, null, false);

        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatModel chatModel = chatList.get(position);
        holder.tv1.setText(chatModel.getShop_name());
        holder.tv2.setText(chatModel.getText_message());
        holder.tv3.setText(chatModel.getText_time());
        holder.CImage.setImageResource(chatModel.getShop_logo());

    }


    @Override
    public int getItemCount() {
        return chatList.size();
    }


    //************************************************************************//
    class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3;
        CircleImageView CImage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv_Shope_name);
            tv2 = itemView.findViewById(R.id.tv_message);
            tv3 = itemView.findViewById(R.id.tv_date);
            CImage = itemView.findViewById(R.id.circimage);
        }
        //********************************************************************//
    }
}

