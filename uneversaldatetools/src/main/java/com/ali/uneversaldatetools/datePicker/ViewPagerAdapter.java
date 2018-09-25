package com.ali.uneversaldatetools.datePicker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> _mFragmentList = new ArrayList<>();

    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return _mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return _mFragmentList.size();
    }


    void AddFragment(Fragment fragment) {
        _mFragmentList.add(fragment);
    }
}