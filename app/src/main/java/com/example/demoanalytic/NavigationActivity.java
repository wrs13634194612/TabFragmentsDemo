package com.example.demoanalytic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initViews();
        initFragments();
    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        setFragment(homeFragment);
    }

    private void setFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_home, fragment).commit();
    }

    private void initViews() {
        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavi());
    }


    private class BottomNavi implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.botnav_home:
                    homeFragment = new HomeFragment();
                    setFragment(homeFragment);
                    return true;
                case R.id.botnav_result:
                    resultFragment = new ResultFragment();
                    setFragment(resultFragment);
                    return true;
                default:
                    return false;
            }
        }
    }
}
