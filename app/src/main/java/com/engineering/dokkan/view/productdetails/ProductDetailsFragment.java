package com.engineering.dokkan.view.productdetails;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.RateModel;
import com.engineering.dokkan.utils.Constants;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends BaseFragment {
    RecyclerView reviewRecycView;
    ReviewAdapter adapter;
    RatingBar ratingBar;
    Button arrowBtn1, arrowBtn2, ask_qustion;
    LinearLayout contaner;
    //product
    TextView ProductName, productDescription, productPrice, ProductMaterial, productSize;
    //Shop
    TextView ShopName, ShopLocation;
    ImageView ShopImage , fav ;
    RatingBar ratebarShop ;


    //Reviews
    private EditText review_editText ;
    private Button send_review ;
    private  RatingBar rate_review ;

    //slider
    SliderView sliderView;
    //Counter
    private TextView counterTxt;
    private Button pluButton, minusButton;
    private int counter;
    //firebase
    private DatabaseReference databaseReference;

    ArrayList<ReviewModel> reviewList;
    ReviewModel reviewModel;

    private String prod_id;
    private String shopId ;
    private String currentUserID;

    private ArrayList<RateModel> rateList ;
    private ArrayList<RateModel> rateListShop ;
    private double rateAverage = 0 ;




    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_details;
    }

    @Override
    public void initializeViews(View view) {
        Bundle bundle = getArguments();
        prod_id = bundle.getString("productId" ) ;
        initialize(view);
        sliderWork();
        Log.d(" product ID " , " id " + prod_id);
        retriveProuductData(prod_id);
        RetriveReviewInRecycleView(prod_id);
    }

    @Override
    public void setListeners() {
        ask_qustion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        send_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewRecycView.setVisibility(View.VISIBLE);
                String comment = review_editText.getText().toString();
                float rate = rate_review.getRating();
                String rate_string = "" + rate ;

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);

                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Reviews");
                String key = dbReference.push().getKey();

                HashMap<String,String> map = new HashMap<>();
                map.put("reviewID" , key);
                map.put("productID" , prod_id);
                map.put("shopID" , shopId);
                map.put("userID" , currentUserID);
                map.put("comment" , comment);
                map.put("rate" , rate_string);
                map.put("date" , formattedDate);

                dbReference.child(key).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity() , "Thanks for your Review" , Toast.LENGTH_SHORT).show();
                    }
                });

                review_editText.setText("");
                rate_review.setRating(0);




            }
        });


        //Expandable Reviews
        arrowBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reviewRecycView.getVisibility() == View.GONE) {
                    // TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                    reviewRecycView.setVisibility(View.VISIBLE);
                    arrowBtn1.setBackgroundResource(R.drawable.up_arrow);
                } else {
                    // TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                    reviewRecycView.setVisibility(View.GONE);
                    arrowBtn1.setBackgroundResource(R.drawable.adown_arrow);
                }
            }
        });

        // Expandable Item Details
        arrowBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contaner.getVisibility() == View.GONE) {
//                    TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                    contaner.setVisibility(View.VISIBLE);
                    arrowBtn2.setBackgroundResource(R.drawable.adown_arrow);
                } else {
                    //                 TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                    contaner.setVisibility(View.GONE);
                    arrowBtn2.setBackgroundResource(R.drawable.up_arrow);
                }
            }
        });
    }

    private void initialize(View view) {
        currentUserID= FirebaseAuth.getInstance().getCurrentUser().getUid();

        fav = view.findViewById(R.id.fav_inProductDetails);
        ratebarShop = view.findViewById(R.id.rateBarforShop);

        //slider
        sliderView = view.findViewById(R.id.imageSlider);
        //button ask qustion
        ask_qustion = view.findViewById(R.id.askQuestion_button);
        //counter
        counterTxt = view.findViewById(R.id.counter_txt);
        minusButton = view.findViewById(R.id.minus_button);
        minusButton.setOnClickListener(clickListener);
        pluButton = view.findViewById(R.id.plus_button);
        pluButton.setOnClickListener(clickListener);
        //Expand Review&Detials
        arrowBtn1 = view.findViewById(R.id.expand_more);
        arrowBtn2 = view.findViewById(R.id.expand_detials);
        //recyclerView
        reviewRecycView = view.findViewById(R.id.recycler);
        contaner = view.findViewById(R.id.contaner);
        ratingBar = view.findViewById(R.id.rate_bar_Item);

        //RatingBar For  The Item
        RatingBar rat_bar_item = view.findViewById(R.id.rate_bar_Item);
        float ratingNumber = rat_bar_item.getRating();
        //product
        ProductName = view.findViewById(R.id.item_name);
        productPrice = view.findViewById(R.id.item_price);
        productDescription = view.findViewById(R.id.description_txt);
        ProductMaterial = view.findViewById(R.id.item_material);
        productSize = view.findViewById(R.id.item_size);
        //shop
        ShopName = view.findViewById(R.id.shop_name);
        ShopImage = view.findViewById(R.id.shop_Image);
        ShopLocation = view.findViewById(R.id.location_txt);

        //review
        review_editText = view.findViewById(R.id.et_write_review);
        send_review = view.findViewById(R.id.send_btn);
        rate_review = view.findViewById(R.id.rate_bar_review);
        reviewList = new ArrayList<>();

        rateList = new ArrayList<>();
        rateListShop = new ArrayList<>();


    }

    private void sliderWork() {
        SliderAdapter adapter2 = new SliderAdapter(getActivity() , prod_id);
        sliderView.setSliderAdapter(adapter2);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
    }

    private void RetriveReviewInRecycleView( String prodID ) {
        Query query = FirebaseDatabase.getInstance().getReference("Reviews")
                .orderByChild("productID").equalTo(prodID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reviewList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        reviewModel = snapshot.getValue(ReviewModel.class);
                        reviewList.add(reviewModel);
                    }
                    adapter = new ReviewAdapter(reviewList);
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
                    reviewRecycView.setLayoutManager(lm);
                    reviewRecycView.setAdapter(adapter);
                    DividerItemDecoration dv;
                    dv = new DividerItemDecoration(reviewRecycView.getContext(),
                            ((LinearLayoutManager) lm).getOrientation());
                    reviewRecycView.addItemDecoration(dv);
                } else {
                    reviewRecycView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void retriveShopData(final String idShop) {
        databaseReference = FirebaseDatabase.getInstance().getReference("shops").child(idShop);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String shName = dataSnapshot.child("shopName").getValue(String.class);
                ShopName.setText(shName);

                String shImage = dataSnapshot.child("shopImage").getValue(String.class);
                Picasso.get().load(shImage).into(ShopImage);

                String shLocation = dataSnapshot.child("location").getValue(String.class);
                ShopLocation.setText(shLocation);

                DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference("RatedList")
                        .child(idShop).child("ListOfRated");
                dbreference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        rateListShop.clear();
                        if ( dataSnapshot.exists()){
                            for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                                RateModel rateModel = snapshot.getValue(RateModel.class);
                                rateAverage = rateAverage + rateModel.getRate() ;
                                rateListShop.add(rateModel);
                            }
                            ratebarShop.setRating( (float)(rateAverage / rateListShop.size() ) );
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void retriveProuductData(final String productId) {
        databaseReference = FirebaseDatabase.getInstance().getReference("products").child(productId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pName = dataSnapshot.child("name").getValue(String.class);
                ProductName.setText(pName);

                String pPrice = dataSnapshot.child("price").getValue(String.class);
                productPrice.setText(pPrice );

                String pDescription = dataSnapshot.child("description").getValue(String.class);
                productDescription.setText(pDescription);

                String pMaterial = dataSnapshot.child("materials").getValue(String.class);
                ProductMaterial.setText(pMaterial);

                String pSize = dataSnapshot.child("size").getValue(String.class);
                productSize.setText(pSize);

                 shopId = dataSnapshot.child("shopId").getValue(String.class);
                retriveShopData(shopId);

                DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference("RatedList")
                        .child(productId).child("ListOfRated");
                dbreference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        rateList.clear();
                        if ( dataSnapshot.exists()){
                            for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                                RateModel rateModel = snapshot.getValue(RateModel.class);
                                rateAverage = rateAverage + rateModel.getRate() ;
                                rateList.add(rateModel);
                            }
                            ratingBar.setRating( (float)(rateAverage / rateList.size() ) );

                            DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                                    .getReference("products").child(productId);
                            databaseReference.child("rate").setValue(  (float)(rateAverage / rateList.size() )  );



                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                final Query query = FirebaseDatabase.getInstance().getReference("Users")
                        .child(currentUserID).child("FavList")
                        .orderByChild("itemId").equalTo( productId );
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ( dataSnapshot.exists() ){
                            fav.setImageResource(R.drawable.fav_icon);
                        } else {
                            fav.setImageResource(R.drawable.ic_favorite_empty);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //counter
    private void minusCounter() {
        counter--;
        counterTxt.setText(counter + "");
    }

    private void plusCounter() {
        counter++;
        counterTxt.setText(counter + "");
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.minus_button:
                    minusCounter();
                    break;
                case R.id.plus_button:
                    plusCounter();
                    break;
            }
        }
    };


}
