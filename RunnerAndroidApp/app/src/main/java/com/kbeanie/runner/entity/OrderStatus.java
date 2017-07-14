package com.kbeanie.runner.entity;

/**
 * Created by vidushi on 19/6/17.
 */

public interface OrderStatus {
    int STATUS_ORDERED = 0;
    int STATUS_FULFILLED = 1;
    int STATUS_OUT_FOR_DELIVERY = 2;
    int STATUS_DELIVERED = 3;
    int STATUS_CLOSED = 4;
}
