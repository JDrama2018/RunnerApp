package com.kbeanie.runner.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kbeanie.runner.entity.OrderStatus;
import com.kbeanie.runner.fragments.OrdersFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vidushi on 20/6/17.
 */

public class OrdersPagerAdapter extends FragmentPagerAdapter {

    private final static int PAGES = 3;
    private List<OrdersFragment> fragments;
    private String[] titles = {"Unfulfilled (2)", "Filled (1)", "Closed (1)"};

    public OrdersPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        OrdersFragment unfulfilledOrders = new OrdersFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("status", OrderStatus.STATUS_ORDERED);
        unfulfilledOrders.setArguments(bundle1);

        OrdersFragment fulfilledOrders = new OrdersFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("status", OrderStatus.STATUS_FULFILLED);
        fulfilledOrders.setArguments(bundle2);

        OrdersFragment closedOrders = new OrdersFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("status", OrderStatus.STATUS_CLOSED);
        closedOrders.setArguments(bundle3);

        fragments.add(unfulfilledOrders);
        fragments.add(fulfilledOrders);
        fragments.add(closedOrders);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
