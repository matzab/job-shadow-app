package hkr.da224a.jobshadow.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hkr.da224a.jobshadow.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessDetailFragment extends Fragment {


    public BusinessDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_detail, container, false);
    }

}
