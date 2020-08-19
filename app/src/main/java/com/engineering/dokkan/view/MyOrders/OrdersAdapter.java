package com.engineering.dokkan.view.MyOrders;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.OrdersModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
public class OrdersAdapter extends RecyclerView.Adapter<OrdersViewholder> {
    List<OrdersModel> Orders;

    public OrdersAdapter(List<OrdersModel> orders) {
        Orders = orders;
    }


    @NonNull
    @Override
    public OrdersViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_orders,parent,false);
        return new OrdersViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewholder holder, int position) {
        Picasso.get()
                .load(Orders.get(position).getImage_url()).into(holder.circleImageView);
        holder.orderDate.setText(Orders.get(position).getOrderDate());
        holder.orderCount.setText(Orders.get(position).getOrderCount()+"item");
        holder.orderPrice.setText("Total:$"+Orders.get(position).getOrderPrice());
        holder.orderStatus.setText(Orders.get(position).getOrderStatus());

//"Total:$
        //
    }

    @Override
    public int getItemCount() {
        return Orders.size();
    }
}


class OrdersViewholder extends RecyclerView.ViewHolder {
    CircleImageView circleImageView;
    TextView orderDate,orderCount,orderPrice;
    Button orderStatus;

    public OrdersViewholder(@NonNull View itemView) {
        super(itemView);
        circleImageView=itemView.findViewById(R.id.image_id);
        orderDate=itemView.findViewById(R.id.orderdate_id);
        orderCount=itemView.findViewById(R.id.ordercount_id);
        orderPrice=itemView.findViewById(R.id.orderprice_id);
        orderStatus=itemView.findViewById(R.id.button_id);

    }
}



