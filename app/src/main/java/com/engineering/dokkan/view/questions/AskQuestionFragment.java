package com.engineering.dokkan.view.questions;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.engineering.dokkan.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AskQuestionFragment extends Fragment {


    public AskQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_askquestion_frag, container, false);
    }

}
