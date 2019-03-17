package hkr.da224a.jobshadow.activities.offer_activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.model.Offer;

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


        Intent intent = getIntent();
        Offer offer = (Offer) intent.getParcelableExtra("selected_offer");

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
