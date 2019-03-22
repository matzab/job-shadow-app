package hkr.da224a.jobshadow.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.fragments.Adapters.OfferListAdapter;
import hkr.da224a.jobshadow.model.Application;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.utils.SQLiteDatabaseHelper;

public class MyApplicationsActivity extends AppCompatActivity {

    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_applications);

        setupActionBar();

        userID = getIntent().getIntExtra("userID",0);

        SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(this);




        ArrayList<Application> appliedApplicationArrayList = new ArrayList<>();

        ArrayList<Application> applicationArrayList = sqLiteDatabaseHelper.getAllApplications();

        for(int i = 0; i < applicationArrayList.size(); i++){
            if(applicationArrayList.get(i).getStudentID() == userID){
                appliedApplicationArrayList.add(applicationArrayList.get(i));
            }
        }



        ArrayList<Offer> offerArrayList = sqLiteDatabaseHelper.getAllOffers();

        ArrayList<Offer> appliedOfferArrayList = new ArrayList<>();

        for(int i = 0; i < offerArrayList.size(); i++){
            boolean found = false;
            for(int j = 0; j < appliedApplicationArrayList.size(); j++){
                if(appliedApplicationArrayList.get(j).getOfferID() == offerArrayList.get(i).getOfferID()){
                    found = true;
                }
            }
            if(found){
                appliedOfferArrayList.add(offerArrayList.get(i));
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.offer_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new OfferListAdapter(this, appliedOfferArrayList, "student", userID);
        recyclerView.setAdapter(mAdapter);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("My Applications");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
