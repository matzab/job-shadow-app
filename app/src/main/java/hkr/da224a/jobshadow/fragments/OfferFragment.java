package hkr.da224a.jobshadow.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.offer_activities.OfferListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferFragment extends Fragment {

    private String[] temporaryData = {"HELLO", "THIS", "IS", "A", "SET", "OF", "TEST", "DATA"};

    public OfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.offer_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new OfferListAdapter(this.getActivity(), temporaryData);
        recyclerView.setAdapter(mAdapter);
    }
}
