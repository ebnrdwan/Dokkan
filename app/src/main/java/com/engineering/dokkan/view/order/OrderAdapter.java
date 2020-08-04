package com.engineering.dokkan.view.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.OrderItemModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderAdapter  extends RecyclerView.Adapter<OrderAdapter.orderHolder> {
    private ArrayList<OrderItemModel>orderList;
    private ItemClickListener onItemClickListener;

    DatabaseReference databaseReference;

    public OrderAdapter(ArrayList<OrderItemModel> orderList , ItemClickListener onItemClickListener) {
        this.orderList = orderList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public orderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderAdapter.orderHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderAdapter.orderHolder holder, final int position) {
        holder.setItemClicked(orderList.get(position));
        Picasso.get().load(orderList.get(position).getProductImage()).into(holder.itemImage);
        holder.itemName.setText(orderList.get(position).getProductName());
        holder.shopName.setText(orderList.get(position).getShopname());

        setQuantityAndPrice( orderList.get(position) , holder.counterTxt , holder.itemPrice );
        holder.pluButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference("products");
                databaseReference.child(orderList.get(position).getProductID()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String mainPrice = dataSnapshot.child("price").getValue(String.class);
                        int quantity = orderList.get(position).getQuantity();
                        quantity++ ;
                        int newQuantityPrice = Integer.parseInt(mainPrice) * quantity ;


                        databaseReference = FirebaseDatabase.getInstance().getReference("cartList");
                        databaseReference.child(orderList.get(position).getKey())
                                .child("quantity").setValue(quantity).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });


                        databaseReference.child(orderList.get(position).getKey())
                                .child("quantityPrice").setValue(newQuantityPrice+"").addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference("products");
                databaseReference.child(orderList.get(position).getProductID()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String mainPrice = dataSnapshot.child("price").getValue(String.class);
                        int quantity = orderList.get(position).getQuantity();
                        if ( quantity >1 ){
                            quantity-- ;
                            int newQuantityPrice = Integer.parseInt(mainPrice) * quantity ;

                            databaseReference = FirebaseDatabase.getInstance().getReference("cartList");
                            databaseReference.child(orderList.get(position).getKey())
                                    .child("quantity").setValue(quantity).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            });

                            databaseReference.child(orderList.get(position).getKey())
                                    .child("quantityPrice").setValue(newQuantityPrice+"").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

             //delete node
             holder.delete.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
                 deleteNode(orderList.get(position).getProductID());
                 orderList.remove(orderList.get(position));
               }
               });

    }

    private void deleteNode(String productID) {
        DatabaseReference rem = FirebaseDatabase.getInstance().getReference("cartList").child(productID);
        rem.removeValue();

    }

    private void setQuantityAndPrice(final OrderItemModel orderItemModel, final TextView counterTxt , final TextView price) {

        databaseReference = FirebaseDatabase.getInstance().getReference("cartList");
        databaseReference.child(orderItemModel.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String newQuantityPRice = dataSnapshot.child("quantityPrice").getValue(String.class);
                int quant = dataSnapshot.child("quantity").getValue(Integer.class);

                counterTxt.setText(quant + "");
                orderItemModel.setQuantity(quant);

                price.setText(newQuantityPRice + " L.E");
                orderItemModel.setQuantityPrice(newQuantityPRice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    class orderHolder extends RecyclerView.ViewHolder {
        View rootView ;
        ItemClickListener itemClickListener;
        ImageView itemImage;
        TextView itemName ;
        TextView shopName ;
        TextView itemPrice ;
        TextView counterTxt;
        Button pluButton, minusButton;
        ImageButton delete;



        public orderHolder(@NonNull View itemView ,final ItemClickListener itemClickListener) {
            super(itemView);
            rootView = itemView ;
            this.itemClickListener = itemClickListener;
            itemImage = itemView.findViewById(R.id.product_image);
            itemName = itemView.findViewById(R.id.item_name);
            shopName = itemView.findViewById(R.id.shop_name);
            itemPrice = itemView.findViewById(R.id.price);
            counterTxt = itemView.findViewById(R.id.counter_txt);
            pluButton = itemView.findViewById(R.id.plus_button);
            minusButton = itemView.findViewById(R.id.minus_button);
            delete = itemView.findViewById(R.id.delete);
        }

        public void setItemClicked(final OrderItemModel item) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }

    public interface ItemClickListener {
        void onItemClick(OrderItemModel item);
    }

}

