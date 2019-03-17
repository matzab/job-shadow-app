package hkr.da224a.jobshadow.activities.offer_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.business_activities.BusinessMainActivity;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.utils.FirebaseDatabaseHelper;
import hkr.da224a.jobshadow.utils.SQLiteDatabaseHelper;

public class OfferEditActivity extends AppCompatActivity {

    EditText offerTitle;
    EditText offerLocation;
    EditText offerDescription;
    EditText offerLength;
    EditText offerPositionQuals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_edit);

        offerTitle = (EditText) findViewById(R.id.offer_title_text);
        offerLocation = (EditText) findViewById(R.id.offer_location_text);
        offerDescription = (EditText) findViewById(R.id.offer_description_text);
        offerLength = (EditText) findViewById(R.id.offer_length_text);
        offerPositionQuals = (EditText) findViewById(R.id.offer_position_quals_text);

        Offer offer = getIntent().getParcelableExtra("current_offer");
        offerTitle.setText(offer.getOfferTitle());
        offerLocation.setText(offer.getOfferLocation());
        offerDescription.setText(offer.getDescription());
        offerLength.setText(offer.getOfferLength());
        offerPositionQuals.setText(offer.getOfferPositionQuals());

    }

    public void onSaveButtonClick(View v){



        if(!offerTitle.getText().toString().equals("") &&
                !offerLocation.getText().toString().equals("") &&
                !offerDescription.getText().toString().equals("") &&
                !offerLength.getText().toString().equals("") &&
                !offerPositionQuals.getText().toString().equals("")){


            System.out.println("Creating new offer");

            Offer offer = new Offer();

            SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(this);

            ArrayList<Offer> offerList = sqLiteDatabaseHelper.getAllOffers();
            int highest = 0;
            for(int i = 0; i < offerList.size(); i++){
                if(offerList.get(i).getOfferID() > highest){
                    highest = offerList.get(i).getOfferID();
                }
            }

            offer.setOfferID(highest+1);
            offer.setBusinessID(getIntent().getIntExtra("businessID",0));
            offer.setOfferTitle(offerTitle.getText().toString());
            offer.setOfferLocation(offerLocation.getText().toString());
            offer.setDescription(offerDescription.getText().toString());
            offer.setOfferLength(offerLength.getText().toString());
            offer.setOfferPositionQuals(offerPositionQuals.getText().toString());
            offer.setDateCreated(new Timestamp(new Date().getTime()));


            sqLiteDatabaseHelper.addOffer(offer);

            FirebaseDatabaseHelper.createNewOffer(offer);

            Intent intent = new Intent(OfferEditActivity.this, BusinessMainActivity.class);
            OfferEditActivity.this.startActivity(intent);
            Toast.makeText(OfferEditActivity.this, "Offer successfully created.", Toast.LENGTH_SHORT).show();
            finish();



        }else{
            Toast.makeText(OfferEditActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();

        }
    }
}
