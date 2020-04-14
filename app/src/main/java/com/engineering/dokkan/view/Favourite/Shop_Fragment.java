package com.engineering.dokkan.view.Favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.engineering.dokkan.R;

public class Shop_Fragment extends Fragment {

    View v ;

    public Shop_Fragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.shop_fragment,container,false);
        return v ;
    }
}
