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

import java.sql.Timestamp;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.offer_activities.OfferListAdapter;
import hkr.da224a.jobshadow.model.Offer;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferFragment extends Fragment {

    private Offer[] temporaryData =
            {new Offer(1, 1, 1, "Senior Software Engineer", "March 15 - April 7", "5 years of experience in the field of software engineering"
                    , "Kristianstad", "You will join our Senior Software Engineer who is currently working on a new product concerning fridge software.", new Timestamp(1)),
                    new Offer(2, 1, 1, "Java Developer", "March 15 - April 7", "1 year of experience with Java"
                            , "Kristianstad", "You will join our Java Developer who is currently working on managing one of our Java systems.", new Timestamp(1))};

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
