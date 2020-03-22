package com.engineering.dokkan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pkmmte.view.CircularImageView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContantViewHolder> {
    ArrayList<Data> notification_List;


    public RecyclerViewAdapter(ArrayList<Data> notificationList) {
        notification_List = notificationList;
    }

    @NonNull
    @Override
    public ContantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewitem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noification,parent,false);
        return new ContantViewHolder(viewitem);
    }

    @Override
    public void onBindViewHolder(@NonNull ContantViewHolder holder, int position) {
        Data data = notification_List.get(position);
        String t = data.getNotification_name();
        String r = data.getNotificaton_info();
        String d = data.getNotification_date();
        int image = data.getNotificaton_image();
        holder.Text_name.setText(t);
        holder.Text_number.setText(r);
        holder.Text_date.setText(d);
        holder.Text_image.setImageResource(image);


    }

    @Override
    public int getItemCount() {
        return notification_List.size();
    }

    class ContantViewHolder extends RecyclerView.ViewHolder{
        TextView Text_name;
        TextView Text_number;
        CircularImageView Text_image;
        TextView Text_date;
        public ContantViewHolder(@NonNull final View itemView) {
            super(itemView);
            Text_name = itemView.findViewById(R.id.notification_name_id);
            Text_number = itemView.findViewById(R.id.notification_info_id);
            Text_date = itemView.findViewById(R.id.notification_date_id);
            Text_image =itemView.findViewById(R.id.notification_image_id);
        }
    }
}