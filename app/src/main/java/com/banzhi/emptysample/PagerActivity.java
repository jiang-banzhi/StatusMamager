package com.banzhi.emptysample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PagerActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab);
        initFragmemt();
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initFragmemt() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));
            TabFragment sampleFragment = TabFragment.newInstance();
            fragments.add(sampleFragment);
        }
    }
}
