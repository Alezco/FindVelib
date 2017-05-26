package com.example.benjamin.findvelib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.benjamin.findvelib.dbo.Velib;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Velib velib = RequestManager.getInstance().velibList;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        VelibFragment velibFragment = new VelibFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNum", position);
        velibFragment.setArguments(bundle);
        return velibFragment;
    }

    @Override
    public int getCount() {
        return velib.records.size();
    }
}
