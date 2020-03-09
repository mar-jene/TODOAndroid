package org.udg.pds.todoandroid.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.udg.pds.todoandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchedulesFragment extends Fragment {
    GroupCollectionPageAdapter groupCollectionPageAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_schedules, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        groupCollectionPageAdapter = new GroupCollectionPageAdapter(getChildFragmentManager());

        viewPager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tablayoutSchedules);

        viewPager.setAdapter(groupCollectionPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}
