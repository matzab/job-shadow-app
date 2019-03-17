package hkr.da224a.jobshadow.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Timestamp;
import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.fragments.Adapters.OfferListAdapter;
import hkr.da224a.jobshadow.activities.student_activities.StudentMainActivity;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.utils.SQLiteDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferFragment extends Fragment {




    public OfferFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toolbar offerToolbar =  getActivity().findViewById(R.id.toolbar);
        ((StudentMainActivity)getActivity()).setToolbar(offerToolbar);
        return inflater.inflate(R.layout.fragment_offer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(this.getContext());
        ArrayList<Offer> temporaryData = sqLiteDatabaseHelper.getAllOffers();

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.offer_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        System.out.println(temporaryData);
        RecyclerView.Adapter mAdapter = new OfferListAdapter(this.getActivity(), temporaryData, "student");
        recyclerView.setAdapter(mAdapter);
    }
}
