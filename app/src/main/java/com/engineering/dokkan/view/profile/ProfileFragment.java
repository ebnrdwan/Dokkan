package com.engineering.dokkan.view.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.engineering.dokkan.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile , container, false);
        ListView listView = rootView.findViewById(R.id.list);

        ArrayList<String> list = AddingOptionsToList();

        OptionAdapter adapter = new OptionAdapter(getActivity() , list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


            }
        });

        return rootView;

    }
    private ArrayList<String> AddingOptionsToList (){
        final ArrayList<String> list = new ArrayList<String>();
        list.add("My Orders");
        list.add("Messages");
        list.add("Reviews");
        list.add("Help");
        list.add("Languages");
        list.add("My Address Book");
        return list ;
    }

}
