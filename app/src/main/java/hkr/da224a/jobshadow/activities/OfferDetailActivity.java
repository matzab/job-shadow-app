package hkr.da224a.jobshadow.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import hkr.da224a.jobshadow.R;

public class OfferDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        TextView textView = (TextView) findViewById(R.id.text_view);
        Intent intent = getIntent();
        String offerName = intent.getStringExtra("selected_offer");
        System.out.println(offerName);
        textView.setText(offerName);

    }
}
