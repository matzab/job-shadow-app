package hkr.da224a.jobshadow.activities.student_activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.fragments.Adapters.ViewPagerAdapter;
import hkr.da224a.jobshadow.fragments.NotificationsFragment;
import hkr.da224a.jobshadow.fragments.OfferFragment;
import hkr.da224a.jobshadow.fragments.SearchFragment;

public class StudentProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        setupViewPager(viewPager);


        setupViewPager(viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_menu);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

//                switch (tab.getPosition()) {
//                    case 0:
//                        break;
//                    case 1:
//                        break;
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter =
                new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new NotificationsFragment(), "Notifications");
        viewPagerAdapter.addFragment(new OfferFragment(), "Offers");
        viewPagerAdapter.addFragment(new SearchFragment(), "History");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
