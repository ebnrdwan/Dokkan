package com.engineering.dokkan.view.address;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.OrderItemModel;
import com.engineering.dokkan.data.models.viewAddressModel;


import java.util.ArrayList;
import java.util.List;

// iam still working in this class...this is not the final code


public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressHolder> {

    private List<viewAddressModel> addressList;

    public List<viewAddressModel> checkedAddress = new ArrayList<>();

    private int lastSelectedPosition = -1;

    public static  viewAddressModel addressModel ;


    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new AddressAdapter.AddressHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressAdapter.AddressHolder holder, final int position) {

        holder.customer_address.setText(addressList.get(position).getCustomerAddress());
        holder.customer_country.setText(addressList.get(position).getCustomerCountry());
        holder.customer_name.setText(addressList.get(position).getCustomerName());
        holder.customer_number.setText(addressList.get(position).getCustomerNumber());
        holder.radioButton.setChecked(lastSelectedPosition == position);

    }

    @Override
    public int getItemCount() {
        return addressList == null ? 0 : addressList.size();
    }

    public void changeData(List<viewAddressModel> addressList) {
        this.addressList = addressList;
        notifyDataSetChanged();
    }


    class AddressHolder extends RecyclerView.ViewHolder {
        TextView customer_name, customer_address, customer_country, customer_number;
        RadioButton radioButton;


        public AddressHolder(@NonNull View itemView) {
            super(itemView);

            customer_name = itemView.findViewById(R.id.customer_name);
            customer_address = itemView.findViewById(R.id.customer_address);
            customer_country = itemView.findViewById(R.id.customer_country);
            customer_number = itemView.findViewById(R.id.customer_number);
            radioButton = itemView.findViewById(R.id.radio_btn_address);


            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    Log.e("a", addressList.get(getAdapterPosition()).getCustomerName());
                    addressModel = addressList.get(getAdapterPosition());
                    //checkedAddress.add(addressList.get(getAdapterPosition()));
                    notifyDataSetChanged();

                }
            });


        }
    }
}



