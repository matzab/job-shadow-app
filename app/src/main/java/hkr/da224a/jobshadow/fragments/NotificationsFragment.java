package hkr.da224a.jobshadow.fragments;


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
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.student_activities.StudentMainActivity;
import hkr.da224a.jobshadow.fragments.Adapters.NotificationListAdapter;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.utils.SQLiteDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {

    private int studentID;

    public NotificationsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toolbar notificationToolbar =  getActivity().findViewById(R.id.toolbar);
        ((StudentMainActivity)getActivity()).setToolbar(notificationToolbar);

        studentID =((StudentMainActivity) getActivity()).userID;

        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(this.getContext());
        ArrayList<Offer> temporaryData = sqLiteDatabaseHelper.getAllOffers();
        Collections.reverse(temporaryData);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.notification_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        System.out.println(temporaryData);
        RecyclerView.Adapter mAdapter = new NotificationListAdapter(this.getActivity(), temporaryData,"student", studentID);
        recyclerView.setAdapter(mAdapter);
    }
}
