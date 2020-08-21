package com.engineering.dokkan.view.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.engineering.dokkan.R;
import com.engineering.dokkan.data.SharedPreference;
import com.engineering.dokkan.data.models.CartItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.orderHolder> {
    private List<CartItem> cartItems;

    private Context context;
    DatabaseReference databaseReference;


    public CartAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public orderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new CartAdapter.orderHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.orderHolder holder, final int position) {

        Picasso.get().load(cartItems.get(position).getProductImage()).into(holder.itemImage);
        holder.itemName.setText(cartItems.get(position).getProductName());
        holder.shopName.setText(cartItems.get(position).getShopName());

        int price = cartItems.get(position).getProductQuanitity() * cartItems.get(position).getProductPrice();
        holder.itemPrice.setText(price + "L.E");
        holder.counter.setNumber(cartItems.get(position).getProductQuanitity() + "");

        holder.counter.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {


                /*databaseReference = FirebaseDatabase.getInstance().getReference("cartList");
                databaseReference.child(orderList.get(position).getKey())
                        .child("quantity").setValue(newValue+"");

                 */


                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(SharedPreference.getInstance(context).getUser());

                databaseReference.child("cart").child(cartItems.get(position).getProductId()).child("productQuanitity").setValue(newValue);


                int price = newValue * cartItems.get(position).getProductPrice();

                holder.itemPrice.setText(price + "$");

                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(SharedPreference.getInstance(context).getUser());
                databaseReference.child("cart").child(cartItems.get(position).getProductId()).child("sum").setValue(price + "");


            }
        });


        //delete node
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(SharedPreference.getInstance(context).getUser());
                databaseReference.child("cart").child(cartItems.get(position).getProductId()).removeValue();

                //deleteNode(orderList.get(position).getProductID());
                //orderList.remove(orderList.get(position));
            }
        });

    }


    @Override
    public int getItemCount() {
        return cartItems == null ? 0 : cartItems.size();

    }


    public void changeData(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    class orderHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView shopName;
        TextView itemPrice;
        ElegantNumberButton counter;
        ImageButton delete;


        public orderHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.product_image);
            itemName = itemView.findViewById(R.id.item_name);
            shopName = itemView.findViewById(R.id.shop_name);
            itemPrice = itemView.findViewById(R.id.price);
            counter = itemView.findViewById(R.id.counter);
            delete = itemView.findViewById(R.id.delete);
        }


    }
}


