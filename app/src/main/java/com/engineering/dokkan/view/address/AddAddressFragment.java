package com.engineering.dokkan.view.address;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.SharedPreference;
import com.engineering.dokkan.data.models.viewAddressModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddAddressFragment extends Fragment implements View.OnClickListener {


    private ImageView arrowBack;
    private TextView saveAddress;
    private EditText editNameCust;
    private EditText editAddressCust;
    private EditText editCountryCust;
    private EditText editNumberCust;

    private  DatabaseReference databaseReference ;

    public AddAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_address, container, false) ;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        initView(view);
        return view;
    }

    private void initView( final View itemView) {
        arrowBack = itemView.findViewById(R.id.arrow_back);
        saveAddress = itemView.findViewById(R.id.add_save);
        saveAddress.setOnClickListener(this);
        editNameCust =  itemView.findViewById(R.id.cust_name_edit);
        editAddressCust =  itemView.findViewById(R.id.cust_address_edit);
        editCountryCust =  itemView.findViewById(R.id.cust_add_edit);
        editNumberCust =  itemView.findViewById(R.id.cust_number_edit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_save:
                String customerName =editNameCust.getText().toString().trim();
                String customerAddress=editAddressCust.getText().toString().trim();
                String customerCountry=editCountryCust.getText().toString().trim();
                String customerPhone=editNumberCust.getText().toString().trim();
                if(isValidForm(customerName ,customerAddress , customerCountry , customerPhone ))
                   addAddressToDataBase(customerName ,customerAddress , customerCountry , customerPhone);

                break;
            default:
                break;
        }
    }

    NavController getNavController() {
        return Navigation.findNavController(getActivity(), R.id.my_nav_host);}

    private void addAddressToDataBase(String customerName, String customerAddress, String customerCountry, String customerPhone) {
        final viewAddressModel addressModel =new viewAddressModel();
        addressModel.setCustomerAddress(customerAddress);
        addressModel.setCustomerName(customerName);
        addressModel.setCustomerCountry(customerCountry);
        addressModel.setCustomerNumber(customerPhone);
        addressModel.setCustomerID(SharedPreference.getInstance(getContext()).getUser());

        //show your custom progress


        FirebaseDatabase.getInstance().getReference("Users").child(SharedPreference.getInstance(getContext()).getUser())
                .child("addresses").push().setValue(addressModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    // Hide your custom progress
                    getNavController().navigate(R.id.action_addAddress_to_AddressFragment);
                }
            }
        });




    }


    private boolean isValidForm(String customerName, String customerAddress, String customerCountry, String customerPhone) {
        boolean isValid=true;
        if(customerName.isEmpty()) {
            editNameCust.setError("Name Required");
            isValid=false;
        }
        else{
            editNameCust.setError(null);

        }
        if(customerAddress.isEmpty()){
            editAddressCust.setError("Address Required");
            isValid=false;
        }
        else{
            editAddressCust.setError(null);

        }
        if(customerCountry.isEmpty()){
            editCountryCust.setError("Country Required");
            isValid=false;
        }
        else {
            editCountryCust.setError(null);
        }

        if(customerPhone.isEmpty()){
            editNumberCust.setError("Number Required");
            isValid=false;
        }
        else{
            editNumberCust.setError(null);

        }

        return isValid;
    }

}
