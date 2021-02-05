package com.example.birdbreeder.View.Adapters;


import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TabsLayoutAdapter extends FragmentStatePagerAdapter {
    private  List<Fragment> fragmentList ;
    private  List<String> fragmentTitle ;
    private  List<Drawable> fragmentIcons ;


    public TabsLayoutAdapter(FragmentManager fm) {
        super(fm);
          fragmentList = new ArrayList<>();
          fragmentTitle = new ArrayList<>();
           fragmentIcons = new ArrayList<>();
    }// end constructer

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }// end getPageTitle()



    public void addFragment(Fragment fragment, String title , Drawable icon ){
        fragmentList.add(fragment);
        fragmentTitle.add(title);
        fragmentIcons.add(icon);
    }// end addFragment()
    @NotNull
    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }// end getItem()

    @Override
    public int getCount() {

        return fragmentList.size();
    } // end getCount()

    public List<Drawable> getFragmentIcons() {
        return fragmentIcons;
    }

    public void setIcons(TabLayout tabs){
        for (int i =0 ; i < getCount() ; i++) {
            tabs.getTabAt(i).setIcon(getFragmentIcons().get(i));
            tabs.getTabAt(i).setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED);
        }

    }
}//end class
