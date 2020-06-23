package com.engineering.dokkan.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

abstract public class BaseFragment extends Fragment {


    /*================= must be implemented Methods - start=========================*/

    /**
     * his is return the layout Resource id to pass it to onCreateView
     */
    public abstract int getLayoutId();



    /**find your views here,
    *@param view  is the rootView that inflated by fragment
     * use it to find your views --> view.findViewById(id)
     * */
  public   abstract void initializeViews(View view);



  /*adding all view listeners here like on view click or on item click*/
    public abstract void setListeners();




    /*will  be needed in the future isa, not now, */
    private void onActivityReady(Bundle bundle) {
    }


    /*================= must be implemented Methods - End=========================*/





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListeners();
        onActivityReady(savedInstanceState);
    }
}
