package hkr.da224a.jobshadow.activities.student_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.LoginActivity;
import hkr.da224a.jobshadow.fragments.NotificationsFragment;
import hkr.da224a.jobshadow.fragments.OfferFragment;
import hkr.da224a.jobshadow.fragments.SearchFragment;

public class StudentMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer = null;
    BottomNavigationView bottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNav = (BottomNavigationView) findViewById(R.id.navigation_bar);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;

                switch (menuItem.getItemId()) {
                    case R.id.navigation_search:
                        fragment = new SearchFragment();
                        openFragment(fragment);
                        return true;
                    case R.id.navigation_notifications:
                        fragment = new NotificationsFragment();
                        openFragment(fragment);
                        return true;
                    case R.id.navigation_home:
                        fragment = new OfferFragment();
                        openFragment(fragment);
                        return true;
                }
                return false;
            }
        });

    }
    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.const_lay, fragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation_bar view item clicks here.
        int id = item.getItemId();

        Intent intent = null;

        switch (id) {
            case R.id.nav_settings:
                intent = new Intent(StudentMainActivity.this, StudentSettingsActivity.class);
                StudentMainActivity.this.startActivity(intent);
                break;
            case R.id.nav_profile:
                intent = new Intent(StudentMainActivity.this, StudentProfileActivity.class);
                StudentMainActivity.this.startActivity(intent);
                break;
            case R.id.nav_logout:
                intent = new Intent(StudentMainActivity.this, LoginActivity.class);
                StudentMainActivity.this.startActivity(intent);
                finish();
                break;
            case R.id.nav_applications:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
