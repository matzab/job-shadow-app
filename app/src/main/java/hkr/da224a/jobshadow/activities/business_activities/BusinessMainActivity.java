package hkr.da224a.jobshadow.activities.business_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.LoginActivity;
import hkr.da224a.jobshadow.activities.offer_activities.OfferEditActivity;
import hkr.da224a.jobshadow.fragments.Adapters.OfferListAdapter;
import hkr.da224a.jobshadow.model.Business;
import hkr.da224a.jobshadow.model.Offer;
import hkr.da224a.jobshadow.utils.SQLiteDatabaseHelper;

public class BusinessMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_main);

        MobileAds.initialize(this,"ca-app-pub-9133678383325719~9752466165");
        adView=findViewById(R.id.AdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My offers");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email_of_user");
        int ID = 0;

        SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(this);
        ArrayList<Business> businessList = sqLiteDatabaseHelper.getAllBusinesses();
        for(int i = 0; i < businessList.size(); i++){
            if(businessList.get(i).getContactEmail().equals(email)){
                ID = businessList.get(i).getBusinessID();
            }
        }
        final int ID2 = ID;

        ArrayList<Offer> thisOfferList = new ArrayList<>();

        ArrayList<Offer> offerList = sqLiteDatabaseHelper.getAllOffers();

        for(int i = 0; i < offerList.size(); i++){
            if(offerList.get(i).getBusinessID() != ID2){
                thisOfferList.add(offerList.get(i));
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.offer_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new OfferListAdapter(this, thisOfferList, "business");
        recyclerView.setAdapter(mAdapter);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusinessMainActivity.this, OfferEditActivity.class);
                intent.putExtra("ID",ID2);
                intent.putExtra("origin","create");
                intent.putExtra("current_offer", new Offer());
                BusinessMainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();
        String email = intent.getStringExtra("email_of_user");
        int ID = 0;

        SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(this);
        ArrayList<Business> businessList = sqLiteDatabaseHelper.getAllBusinesses();
        for(int i = 0; i < businessList.size(); i++){
            if(businessList.get(i).getContactEmail().equals(email)){
                ID = businessList.get(i).getBusinessID();
            }
        }
        final int ID2 = ID;


        ArrayList<Offer> offerList = sqLiteDatabaseHelper.getAllOffers();
        for(int i = 0; i < offerList.size(); i++){
            if(offerList.get(i).getBusinessID() != ID2){
                offerList.remove(i);
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.offer_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new OfferListAdapter(this, offerList, "business");
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation_bar view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            Intent intent = new Intent(BusinessMainActivity.this, BusinessSettingsActivity.class);
            BusinessMainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(BusinessMainActivity.this, BusinessProfileActivity.class);
            BusinessMainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(BusinessMainActivity.this, LoginActivity.class);
            BusinessMainActivity.this.startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
