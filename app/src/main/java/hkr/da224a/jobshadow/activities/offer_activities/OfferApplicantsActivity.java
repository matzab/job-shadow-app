package hkr.da224a.jobshadow.activities.offer_activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.fragments.Adapters.ApplicantListAdapter;
import hkr.da224a.jobshadow.model.Application;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.model.Student;
import hkr.da224a.jobshadow.utils.SQLiteDatabaseHelper;

public class OfferApplicantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_applicants);
        setupActionBar();

        SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(this);
        
        Offer offer = getIntent().getParcelableExtra("current_offer");
        
        ArrayList<Application> offerApplicationArrayList = new ArrayList<>();
        ArrayList<Application> applicationArrayList = sqLiteDatabaseHelper.getAllApplications();

        for(int i = 0; i < applicationArrayList.size(); i++){
            if(applicationArrayList.get(i).getOfferID() == offer.getOfferID()){
                offerApplicationArrayList.add(applicationArrayList.get(i));
            }
        }

        ArrayList<Student> studentArrayList = sqLiteDatabaseHelper.getAllStudents();
        ArrayList<Student> appliedStudentArrayList = new ArrayList<>();

        for(int i = 0; i < studentArrayList.size(); i++){
            boolean found = false;
            for(int j = 0; j < offerApplicationArrayList.size(); j++){
                if(offerApplicationArrayList.get(j).getStudentID() == studentArrayList.get(i).getStudentID()){
                    found = true;
                }
            }
            if(found){
                appliedStudentArrayList.add(studentArrayList.get(i));
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.applicant_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new ApplicantListAdapter(this, appliedStudentArrayList, "student");
        recyclerView.setAdapter(mAdapter);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Offer Applicants");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
