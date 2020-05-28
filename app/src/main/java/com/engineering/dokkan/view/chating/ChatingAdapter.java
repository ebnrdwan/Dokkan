package com.engineering.dokkan.view.chating;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ChatingModel;

import java.util.ArrayList;
import java.util.List;


public class ChatingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatingModel> msgList;


    ChatingAdapter(List<ChatingModel> msgList) {
        this.msgList = msgList;
    }

    //*************************************************************************************//
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View sendView = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_item_view, parent, false);
            return new SenderViewHolder(sendView); // view holder for normal items
        } else {
            View receiveView = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_item_view, parent, false);
            return new ReciverViewHolder(receiveView); // view holder for header items
        }
    }

    //*************************************************************************************************//
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);

        if (itemType == 0) {
            ((SenderViewHolder) holder).setSendText(msgList.get(position).getMsgContent());
        } else if (itemType == 1) {
            ((ReciverViewHolder) holder).setRecieverText(msgList.get(position).getMsgContent());
        }
    }

    //***********************************************************************************************//
    @Override
    public int getItemViewType(int position) {
        return msgList.get(position).getMsgType();
    }

    //**********************************************************************************************//
    @Override
    public int getItemCount() {
        if (msgList == null) {
            msgList = new ArrayList<>();
        }
        return msgList.size();

    }

//************************************************************************************************//

    public static class SenderViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rightMsgLayout;
        TextView rightMsgTextView;

        SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView != null) {
                rightMsgLayout = itemView.findViewById(R.id.chat_right_msg_layout);
                rightMsgTextView = itemView.findViewById(R.id.chat_right_msg_tv);
            }
        }

        void setSendText(final String text) {
            rightMsgTextView.setText(text);
        }
    }

    //**********************************//
    public static class ReciverViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftMsgLayout;
        TextView leftMsgTextView;

        ReciverViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView != null) {
                leftMsgLayout = itemView.findViewById(R.id.chat_left_msg_layout);
                leftMsgTextView = itemView.findViewById(R.id.chat_left_msg_tv);
            }
        }

        public void setRecieverText(final String text) {
            leftMsgTextView.setText(text);
        }
    }

}