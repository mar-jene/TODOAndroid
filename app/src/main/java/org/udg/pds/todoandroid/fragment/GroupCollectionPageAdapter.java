package org.udg.pds.todoandroid.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class GroupCollectionPageAdapter extends FragmentStatePagerAdapter {
    public GroupCollectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new OwnedGroupsFragment();
                break;
            case 1:
                fragment = new GroupsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "OwnedGroupsFragment";
            case 1:
                return "GroupsFragment";
            default:
                return "";
        }
    }
}
