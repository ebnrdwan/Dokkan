package com.engineering.dokkan.view.orders.allOrders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.OrderItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class AllOrdersAdapter extends RecyclerView.Adapter<AllOrdersAdapter.AllOrdersVHolder>  {

    private List<OrderItemModel> orderItemModels = new ArrayList<>();

    public interface  OnItemClickListner{
        void onItemClick(int pos, OrderItemModel orderItemModel);
    }

    OnItemClickListner onItemClickListener;

    public void setOnItemClickListener(OnItemClickListner onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    @NonNull
    @Override
    public AllOrdersVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllOrdersVHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull AllOrdersVHolder holder, final int position) {


         // picaso for orderDate
        Picasso.get().load("temp").placeholder(R.drawable.icon4).into(holder.customerImage);
        holder.customerName.setText(orderItemModels.get(position).getAddress().getCustomerName());
        holder.customerPhoneNum.setText(orderItemModels.get(position).getAddress().getCustomerNumber());
        String address = orderItemModels.get(position).getAddress().getCustomerCountry() + orderItemModels.get(position).getAddress().getCustomerAddress() ;
        holder.customerAddressLocation.setText(address);
       //holder.orderDate.setText(""/*todo add order date*/);
        Log.e("a",orderItemModels.get(position).getCartItem().size()+"");

        if(onItemClickListener!=null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position,orderItemModels.get(position));
                }
            });




    }

    @Override
    public int getItemCount() {

        return orderItemModels == null ? 0 : orderItemModels.size();
    }

    public void setList(List<OrderItemModel> orderItemModels) {
        this.orderItemModels = orderItemModels;
        notifyDataSetChanged();
    }


    public class AllOrdersVHolder extends RecyclerView.ViewHolder {

        private TextView orderDate;
        private CircleImageView customerImage;
        private TextView customerName;
        private TextView customerPhoneNum;
        private TextView customerAddressLocation;

        public AllOrdersVHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }
        private void initView(@NonNull final View itemView) {
            orderDate =  itemView.findViewById(R.id.tvOrderDateValue);
            customerAddressLocation = (TextView) itemView.findViewById(R.id.tvCustomerAdress);
            customerImage = (CircleImageView) itemView.findViewById(R.id.imgCustomerProfile);
            customerName = (TextView) itemView.findViewById(R.id.tvCustomerName);
            customerPhoneNum = (TextView) itemView.findViewById(R.id.tvCustomerPhone);
        }
    }
}
