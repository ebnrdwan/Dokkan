package com.engineering.dokkan.view.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

// iam still working in this class...this is not the final code


public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressHolder> {

    ArrayList<view_address_item> addressList;
    DatabaseReference databaseReference;
   protected ItemClickListener onItemClickListener;



    public AddressAdapter(ArrayList<view_address_item> addressList, AddressAdapter.ItemClickListener onItemClickListener) {
        this.addressList = addressList;
        this.onItemClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new AddressAdapter.AddressHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressAdapter.AddressHolder holder, final int position) {
        holder.setItemClicked(addressList.get(position));
        holder.customer_name.setText(addressList.get(position).getCustomer_name());
        holder.customer_address.setText(addressList.get(position).getCustomer_address());
        holder.customer_country.setText(addressList.get(position).getCustomer_country());
        holder.customer_number.setText(addressList.get(position).getCustomer_number());
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

     class AddressHolder extends RecyclerView.ViewHolder{
        View rootView ;
       ItemClickListener itemClickListener;
         TextView customer_name;
        TextView customer_address;
        TextView customer_country;
        TextView customer_number;

        public AddressHolder(@NonNull View itemView, final AddressAdapter.ItemClickListener itemClickListener) {
            super(itemView);
            rootView = itemView ;
            this.itemClickListener = itemClickListener;
            customer_name = itemView.findViewById(R.id.customer_name);
            customer_address = itemView.findViewById(R.id.customer_address);
            customer_country = itemView.findViewById(R.id.customer_country);
            customer_number = itemView.findViewById(R.id.customer_number);

        }

         public void setItemClicked(final view_address_item item) {
             rootView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     itemClickListener.onItemClick(item);
                 }
             });
         }
    }
    public interface ItemClickListener {
        void onItemClick(view_address_item item);
    }
    }


