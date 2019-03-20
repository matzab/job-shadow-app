package hkr.da224a.jobshadow.activities.offer_activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.model.Application;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.utils.SQLiteDatabaseHelper;

public class OfferDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setupActionBar();

        TextView offerTitle = (TextView) findViewById(R.id.offer_title_text);
        TextView offerLength = (TextView) findViewById(R.id.offer_length_text);
        TextView offerPositionQuals = (TextView) findViewById(R.id.offer_position_quals_text);
        TextView offerLocation = (TextView) findViewById(R.id.offer_location_text);
        TextView offerDescription = (TextView) findViewById(R.id.offer_description_text);
        Button button = findViewById(R.id.button);

        Intent intent = getIntent();
        final int studentID = intent.getIntExtra("studentID", 0);
        final Offer offer = (Offer) intent.getParcelableExtra("selected_offer");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application application = new Application();

                SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(OfferDetailActivity.this);
                ArrayList<Application> applicationList = sqLiteDatabaseHelper.getAllApplications();
                int highest = 0;
                for (int i = 0; i < applicationList.size(); i++) {
                    if (applicationList.get(i).getApplicationID() > highest) {
                        highest = applicationList.get(i).getApplicationID();
                    }
                }

                application.setApplicationID(highest + 1);
                application.setOfferID(offer.getOfferID());
                application.setStudentID(studentID);

                sqLiteDatabaseHelper.addApplication(application);

                Toast.makeText(OfferDetailActivity.this, "Applied for offer!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        if (intent.getStringExtra("origin").equals("student")) {
            System.out.println("Origin was student.");
            SQLiteDatabaseHelper databaseHelper = new SQLiteDatabaseHelper(this);
            ArrayList<Application> applicationList = databaseHelper.getAllApplications();
            for (int i = 0; i < applicationList.size(); i++) {
                if (applicationList.get(i).getOfferID() == offer.getOfferID() && applicationList.get(i).getStudentID() == studentID) {
                    final Application application = applicationList.get(i);
                    System.out.println("Already applied");
                    button.setText("CANCEL APPLICATION");
                    button.setBackgroundColor(Color.parseColor("#be0000"));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(OfferDetailActivity.this);
                            sqLiteDatabaseHelper.deleteApplication(application);

                            Toast.makeText(OfferDetailActivity.this, "Application canceled.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    break;
                } else {
                    System.out.println("Not yet applied");
                    button.setText("APPLY");
                    button.setBackgroundColor(Color.parseColor("#00bbbb"));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Application application = new Application();

                            SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(OfferDetailActivity.this);
                            ArrayList<Application> applicationList = sqLiteDatabaseHelper.getAllApplications();
                            int highest = 0;
                            for (int i = 0; i < applicationList.size(); i++) {
                                if (applicationList.get(i).getApplicationID() > highest) {
                                    highest = applicationList.get(i).getApplicationID();
                                }
                            }

                            application.setApplicationID(highest + 1);
                            application.setOfferID(offer.getOfferID());
                            application.setStudentID(studentID);

                            sqLiteDatabaseHelper.addApplication(application);

                            Toast.makeText(OfferDetailActivity.this, "Applied for offer!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }
        }

        if (intent.getStringExtra("origin").equals("business")) {
            button.setText("EDIT");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OfferDetailActivity.this, "Please edit your offer.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OfferDetailActivity.this, OfferEditActivity.class);
                    intent.putExtra("origin", "edit");
                    intent.putExtra("current_offer", offer);
                    OfferDetailActivity.this.startActivity(intent);
                    finish();
                }
            });
        }


        offerTitle.setText(offer.getOfferTitle());
        offerLength.setText(offer.getOfferLength());
        offerPositionQuals.setText(offer.getOfferPositionQuals());
        offerLocation.setText(offer.getOfferLocation());
        offerDescription.setText(offer.getDescription());


    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Offer details");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
