package com.engineering.dokkan.view.shop;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.ProductitemModel;
import com.engineering.dokkan.data.models.RateModel;
import com.engineering.dokkan.data.models.ShopProductModel;
import com.engineering.dokkan.data.models.ShopReviewModel;
import com.engineering.dokkan.data.models.ShopModel;
import com.engineering.dokkan.view.base.BaseFragment;
import com.engineering.dokkan.view.home.ProductRecycAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopPageFragment extends BaseFragment {
    private Bundle bundle;

    private static  final int REQUEST_CALL = 1 ;

    //review
    private ArrayList<ShopReviewModel> reviewList;
    private RecyclerView reviewRecyclerView;

    //items
    private ArrayList<ShopProductModel> prodList = new ArrayList<>();
    private RecyclerView productRecyclerView;
    ShopProductRecycAdapter.ItemClickListener ListenerProducts;


    //shop details
    private TextView shopname , location , desc ,about , policies ;
    private Button fblink , instalink ;
    private ImageView shopimg , fav , contactUs , share ;
    private RatingBar ratingBar ;
    private Button copy , call ;
    private TextView number;
    private String fb_link , insta_link , callnum ;

    //deep link
    private String msg ;


    private String currentuserId ;


    RelativeLayout review ;
    RelativeLayout item ;

    private ArrayList<RateModel> ratelist ;
    private double rateAverage = 0;

    public ShopPageFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_page;
    }

    @Override
    public void initializeViews(View view) {
        initialization(view);

        //get shop id from home when click on specific shop
        bundle = getArguments();
        String shop_id = bundle.getString("shop_id");
        showShopDetails(shop_id);
        showShopProducts(shop_id);
        msg = " Welcome to the shop : http://www.dokkan.com/shops/" + shop_id ;

        showShopReviews(shop_id);


    }

    private void initialization(View view) {
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        reviewList = new ArrayList<>();

        ratelist = new ArrayList<>();

        reviewRecyclerView = view.findViewById(R.id.recyclerview_review);

        productRecyclerView = view.findViewById(R.id.recyclerview_item);


        shopimg = view.findViewById(R.id.img_shop) ;
        shopname = view.findViewById(R.id.name_shop);
        location = view.findViewById(R.id.location);
        desc = view.findViewById(R.id.shop_desc);
        ratingBar = view.findViewById(R.id.rating_bar) ;
        about = view.findViewById(R.id.about_tv);
        policies = view.findViewById(R.id.policies_tv);
        fblink = view.findViewById(R.id.tv_fb);
        instalink = view.findViewById(R.id.tv_insta);
        fav = view.findViewById(R.id.favourite_shop);
        contactUs = view.findViewById(R.id.contact_shop);
        share = view.findViewById(R.id.share_shop);

        review = view.findViewById(R.id.relative_review);
        item = view.findViewById(R.id.relative_item);

    }

    private void showShopDetails(final String id) {
        final Query query = FirebaseDatabase.getInstance().getReference("shops")
                .orderByChild("key").equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    final ShopModel shops = snapshot.getValue(ShopModel.class);
                    Picasso.get().load(shops.getShopImage()).into(shopimg);
                    shopname.setText(shops.getShopName());
                    ratingBar.setRating(shops.getRate());
                    location.setText(shops.getLocation());
                    desc.setText(shops.getBio());
                    about.setText(shops.getAbout());
                    policies.setText(shops.getPolicies());

                    fb_link = shops.getFbLink();
                    insta_link = shops.getInstaLink();
                    callnum = shops.getPhoneNum() ;

                    final Query query = FirebaseDatabase.getInstance().getReference("Users")
                            .child(currentuserId).child("FavList")
                            .orderByChild("itemId").equalTo( id );
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if ( dataSnapshot.exists() ){
                                fav.setImageResource(R.drawable.fav_icon);
                                shops.setFav(true);
                            } else {
                                fav.setImageResource(R.drawable.ic_favorite_empty);
                                shops.setFav(false);


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference("RatedList")
                            .child(id).child("ListOfRated");
                    dbreference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ratelist.clear();
                            if ( dataSnapshot.exists()){
                                for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    RateModel rateModel = snapshot.getValue(RateModel.class);
                                    rateAverage = rateAverage + rateModel.getRate() ;
                                    ratelist.add(rateModel);
                                }
                                ratingBar.setRating( (float)(rateAverage / ratelist.size() ) );
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity() , databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void showShopReviews(String shopID) {

        Query query = FirebaseDatabase.getInstance().getReference("Reviews")
                .orderByChild("shopID").equalTo(shopID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    review.setVisibility(View.VISIBLE);
                    reviewRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ShopReviewModel reviewModel = snapshot.getValue(ShopReviewModel.class);
                        reviewList.add(reviewModel);
                    }
                    ShopReviewRecycAdapter adapter = new ShopReviewRecycAdapter(reviewList);
                    reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    reviewRecyclerView.setAdapter(adapter);
                    DividerItemDecoration dv;
                    dv = new DividerItemDecoration(reviewRecyclerView.getContext(),
                            ((LinearLayoutManager) new LinearLayoutManager(getActivity())).getOrientation());
                    reviewRecyclerView.addItemDecoration(dv);

                } else{
                    review.setVisibility(View.GONE);
                    reviewRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity() , databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    private void showShopProducts(String id) {
          prodList = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference("products")
                .orderByChild("shopId").equalTo(id);
        if ( query != null) {
            item.setVisibility(View.VISIBLE);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ShopProductModel productModel = snapshot.getValue(ShopProductModel.class);
                            prodList.add(productModel);
                        }
                        ShopProductRecycAdapter adapter = new ShopProductRecycAdapter(getContext(), prodList, ListenerProducts);
                        productRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        productRecyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(getActivity() , "Still no products in this shop.." , Toast.LENGTH_SHORT).show();
                        item.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
        }


    }

    @Override
    public void setListeners() {
        ListenerProducts = new ShopProductRecycAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ShopProductModel item) {
                        Bundle bundle = new Bundle();
                        bundle.putString("productId", item.getProductId());
                        navigateTo(R.id.action_global_to_ProductDetails, null, null, bundle);



                    }
        };



        fblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(fb_link) ;
                Intent i = new Intent(Intent.ACTION_VIEW , uri) ;
                startActivity(i);
            }
        });

        instalink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(insta_link);
                Intent i = new Intent(Intent.ACTION_VIEW , uri) ;
                startActivity(i);
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setChooserTitle("Share URL")
                        .setText(msg)
                        .startChooser();
            }
        });

    }

    private void openDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater() ;
        View view = inflater.inflate(R.layout.layout_call_dialog , null);
        final AlertDialog.Builder mB =new AlertDialog.Builder(getActivity());

        mB.setTitle("The Phone Number :");
        mB.setView(view);
        mB.setCancelable(false);
        mB.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog m = mB.create();
        mB.show();

        copy = view.findViewById(R.id.copy);
        call = view.findViewById(R.id.call_us);
        number = view.findViewById(R.id.number);

        number.setText(callnum);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }

        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity() ;
                ClipboardManager clipboard = (ClipboardManager)
                        context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("phone number", callnum);
                clipboard.setPrimaryClip(clip);
                clip.getDescription();
                Toast.makeText(getActivity() , "copied" , Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(getActivity() , Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(getActivity() , new String[]{Manifest.permission.CALL_PHONE} ,REQUEST_CALL);

        } else {
            String dial = "tel:" + callnum;
            startActivity(new Intent(Intent.ACTION_CALL , Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            } else {
                Toast.makeText(getActivity() ,"PERMISSION DENIED" ,Toast.LENGTH_SHORT).show();
            }
        }

    }

}
