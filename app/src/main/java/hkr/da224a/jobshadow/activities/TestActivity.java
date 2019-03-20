package hkr.da224a.jobshadow.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.model.Application;
import hkr.da224a.jobshadow.model.Business;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.model.OfferCategory;
import hkr.da224a.jobshadow.model.Student;
import hkr.da224a.jobshadow.utils.FirebaseDatabaseHelper;

public class TestActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Search Student by ID
        Button searchStudentButton = findViewById(R.id.searchStudentButton);
        searchStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("TAG", " " + FirebaseDatabaseHelper.getStudent(1).toString());
            }
        });

        // Search Student by Email ID
        Button searchEmailButton = findViewById(R.id.searchEmailButton);
        searchEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("TAG", " " + FirebaseDatabaseHelper.getStudent("s@t.com").toString());
            }
        });

        // Get all students
        ArrayList<Student> students = FirebaseDatabaseHelper.getAllStudents();

        //Update User by object
        FirebaseDatabaseHelper.updateStudent(new Student(1, "123123", "2", "2", 2,
                "s@t.com", "2", "2", 2, "2", "2", "2"));

        // Delete user by object
//        FirebaseDatabaseHelper.deleteStudent(new Student(1, "123123", "2", "2", 2,
//                "s@t.com", "2", "2", 2, "2", "2", "2"));

        // Search Offer by Email ID
        Button searchOfferButton = findViewById(R.id.searchOfferButton);
        searchOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("TAG", " " +
                        FirebaseDatabaseHelper.getOffer(1).toString());
            }
        });

        // Updates the Offer by Object
        FirebaseDatabaseHelper.updateOffer(new Offer(1, 2, 2,
                "2", "2", "2", "2", "2"));

        // Delete Offer by Offer ID
//        FirebaseDatabaseHelper.deleteOffer(new Offer(1,2,2,
//                "2","2","2","2","2"));

        // Get all Offers
        ArrayList<Offer> offers = FirebaseDatabaseHelper.getAllOffers();

        // Search Business by ID
        Button searchBbyIDButton = findViewById(R.id.searchBbyIDButton);
        searchBbyIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("TAG", " " + FirebaseDatabaseHelper.getBusiness(1).toString());
            }
        });

        // Search Business by Email ID
        Button searchBbyEmailButton = findViewById(R.id.searchBbyEmailButton);
        searchBbyEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("TAG", " " + FirebaseDatabaseHelper.getBusiness("b@t.com").toString());
            }
        });

        // Updates the Offer by Object
        FirebaseDatabaseHelper.updateBusiness(new Business(1, "2", "2",
                "2", 2, "2", "b@t.com", true, "2"));

        // Delete Offer by Offer ID
//        FirebaseDatabaseHelper.deleteBusiness(new Business(1, "2", "2",
//                "2", 2, "2", "2", true, "2"));

        // Get all businesses
        ArrayList<Business> businesses = FirebaseDatabaseHelper.getAllBusinesses();

        // ArrayList of Offer objects where the business ID is the entered business ID
        ArrayList<Offer> offerArrayList = FirebaseDatabaseHelper.getBusinessOffers(2);

        // Update Application
        FirebaseDatabaseHelper.updateApplication(new Application(1, 1, 1));

        // Delete Application
//        FirebaseDatabaseHelper.deleteApplication(new Application(1,1,1));

        // Get All Offer Categories
        ArrayList<OfferCategory> offerCategories = FirebaseDatabaseHelper.getAllOfferCategories();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
