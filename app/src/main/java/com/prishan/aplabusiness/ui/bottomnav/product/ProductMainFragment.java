package com.prishan.aplabusiness.ui.bottomnav.product;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;
import com.prishan.aplabusiness.R;
import com.prishan.aplabusiness.databinding.ProductMainFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductMainFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    private static final String TAG = "ProductMainFragment";
    ViewPagerAdapter adapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    public static ProductMainFragment newInstance() {
        return new ProductMainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.product_main_fragment, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabs);

        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new ProductFragment(), "Product");
        adapter.addFrag(new CategoryFragment(), "Category");
        viewPager.setAdapter(adapter);
        //give the tab layout the view pager
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorBlackFaded),getResources().getColor(R.color.colorBlackFaded));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.e(TAG,"onTabSelected==>"+tab.getPosition());
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.e(TAG,"onTabUnselected==>"+tab.getPosition());
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.e(TAG,"onTabReselected==>"+tab.getPosition());
    }

    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }



}