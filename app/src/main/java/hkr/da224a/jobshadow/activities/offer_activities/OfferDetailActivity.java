package hkr.da224a.jobshadow.activities.offer_activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Button button = findViewById(R.id.button);


        Intent intent = getIntent();
        final Offer offer = (Offer) intent.getParcelableExtra("selected_offer");

        if(intent.getStringExtra("origin").equals("student")){
            button.setText("APPLY");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OfferDetailActivity.this, "Applied for offer (NOT OPERATIONAL YET)", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if(intent.getStringExtra("origin").equals("business")){
            button.setText("EDIT");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OfferDetailActivity.this, "Please edit your offer.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OfferDetailActivity.this, OfferEditActivity.class);
                    intent.putExtra("origin","edit");
                    intent.putExtra("current_offer",offer);
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
