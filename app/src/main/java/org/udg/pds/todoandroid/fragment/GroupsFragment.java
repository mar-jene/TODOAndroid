package org.udg.pds.todoandroid.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.udg.pds.todoandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {

    public GroupsFragment() {}


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_groups_fragment, container, false);
    }
}
