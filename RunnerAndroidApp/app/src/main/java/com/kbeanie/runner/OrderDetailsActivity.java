package com.kbeanie.runner;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.kbeanie.runner.adapters.MessagesAdapter;
import com.kbeanie.runner.adapters.OrderItemsAdapter;
import com.kbeanie.runner.data.OrdersProvider;
import com.kbeanie.runner.entity.Order;
import com.kbeanie.runner.entity.OrderStatus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by vidushi on 21/6/17.
 */

public class OrderDetailsActivity extends RunnerActivity {
    @BindView(R.id.rvOrderItems)
    RecyclerView rvOrderItems;

    @BindView(R.id.rvMessages)
    RecyclerView rvMessages;

    @BindView(R.id.btnOrderAction)
    Button btnOrderAction;

    @BindView(R.id.btnLeaveMessage)
    Button btnLeaveMessage;

    private Order order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        rvOrderItems.setLayoutManager(lm);

        OrderItemsAdapter adapter = new OrderItemsAdapter(this, OrdersProvider.getSampleItems());
        rvOrderItems.setAdapter(adapter);

        LinearLayoutManager mlm = new LinearLayoutManager(this);
        rvMessages.setLayoutManager(mlm);
        MessagesAdapter messagesAdapter = new MessagesAdapter(this, OrdersProvider.getSampleMessages());
        rvMessages.setAdapter(messagesAdapter);

        order = RunnerApplication.selectedOrder;
        // Button Tinting
        if (order.getStatus() == OrderStatus.STATUS_FULFILLED) {
            btnOrderAction.setText("Close Order");
            btnOrderAction.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.colorDarkGreen), PorterDuff.Mode.MULTIPLY);
        } else if (order.getStatus() == OrderStatus.STATUS_CLOSED) {
            btnOrderAction.setText("Unclose Order");
        }
    }

    @OnClick(R.id.ivClose)
    public void onCloseClick() {
        finish();
    }

    @OnClick(R.id.btnLeaveMessage)
    public void onLeaveMessage() {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnOrderAction)
    public void onOrderAction() {
        if(order.getStatus() == OrderStatus.STATUS_ORDERED){
            btnLeaveMessage.setVisibility(View.GONE);
            btnOrderAction.setText("Order filled");
            showNotification("Hey Daniel, your food is coming. Please come to the steps down below.");
        }
    }
}
