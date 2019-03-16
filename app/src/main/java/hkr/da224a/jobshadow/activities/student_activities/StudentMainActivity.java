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
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Stack;

import hkr.da224a.jobshadow.NoSwipeViewPager;
import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.LoginActivity;
import hkr.da224a.jobshadow.fragments.Adapters.ViewPagerAdapter;
import hkr.da224a.jobshadow.fragments.NotificationsFragment;
import hkr.da224a.jobshadow.fragments.OfferFragment;
import hkr.da224a.jobshadow.fragments.SearchFragment;

public class StudentMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Stack<Integer> backStack = new Stack<>();
    private DrawerLayout drawer = null;
    private BottomNavigationView bottomNav;
    private NoSwipeViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (NoSwipeViewPager) findViewById(R.id.main_menu_holder);
        viewPager.setPagingEnabled(false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int currentItem = viewPager.getCurrentItem();
                if (backStack.empty())
                    backStack.push(0);

                if(backStack.contains(currentItem)) {
                    backStack.remove(backStack.indexOf(currentItem));
                    backStack.push(currentItem);
                }else{
                    backStack.push(currentItem);
                }

                bottomNav.setSelectedItemId(currentItem);
            //    bottomNav.set

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        setupViewPager(viewPager);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        getSupportActionBar().setTitle("Home");
                        return true;
                    case R.id.navigation_search:

                        viewPager.setCurrentItem(1);
                        getSupportActionBar().setTitle("Search");
                        return true;
                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(2);
                        getSupportActionBar().setTitle("Notifications");
                        return true;
                }
                return false;
            }
        });
    }


//    private void openFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.replace(R.id.main_menu_holder, fragment);
//        fragmentTransaction.addToBackStack(null);
//
//        fragmentTransaction.commit();
//    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }

        if(backStack.size() > 1){
            backStack.pop();
            viewPager.setCurrentItem(backStack.lastElement());
            Menu menu  = bottomNav.getMenu();
            MenuItem item = menu.getItem(backStack.lastElement());
            item.setChecked(true);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void setupViewPager(NoSwipeViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter =
                new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new OfferFragment(), "Offers");
        viewPagerAdapter.addFragment(new SearchFragment(), "Search");
        viewPagerAdapter.addFragment(new NotificationsFragment(), "Notifications");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
