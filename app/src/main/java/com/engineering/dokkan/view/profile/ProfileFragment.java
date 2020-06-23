package com.engineering.dokkan.view.profile;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;

import java.util.ArrayList;

public class ProfileFragment extends BaseFragment {

    public ProfileFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initializeViews(View view) {
        ListView listView = view.findViewById(R.id.list);
        ArrayList<String> list = AddingOptionsToList();
        OptionAdapter adapter = new OptionAdapter(getActivity() , list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onOptionClickListener);
    }

    private AdapterView.OnItemClickListener onOptionClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        }
    };

    @Override
    public void setListeners() {

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
