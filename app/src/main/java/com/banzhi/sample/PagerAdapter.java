package com.banzhi.sample;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * <pre>
 * @author : No.1
 * @time : 2018/9/18.
 * @desciption :
 * @version :
 * </pre>
 */

public class PagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "TAB" + position;
    }
}
