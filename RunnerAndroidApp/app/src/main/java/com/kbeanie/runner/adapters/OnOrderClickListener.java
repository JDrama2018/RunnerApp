package com.kbeanie.runner.adapters;

import com.kbeanie.runner.entity.Order;

/**
 * Created by vidushi on 21/6/17.
 */

public interface OnOrderClickListener {

    void onOrderClicked(Order order);
    void onOrderDeliverClicked(Order order);
}
