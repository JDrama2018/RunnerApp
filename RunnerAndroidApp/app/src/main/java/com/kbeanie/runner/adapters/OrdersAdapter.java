package com.kbeanie.runner.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kbeanie.runner.R;
import com.kbeanie.runner.data.OrdersProvider;
import com.kbeanie.runner.entity.Order;
import com.kbeanie.runner.entity.OrderItem;
import com.kbeanie.runner.entity.OrderStatus;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Optional;

import static android.view.View.GONE;

/**
 * Created by vidushi on 20/6/17.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersVH> {

    private final static String TAG = OrdersAdapter.class.getSimpleName();

    private final static int TIME_DIFF_RED = 15;
    private final static int TIME_DIFF_YELLOW = 10;
    private final static int TIME_DIFF_GREEN = 5;

    private OnOrderClickListener listener;

    private final Context context;
    private final int status;
    private List<Order> orders;

    public OrdersAdapter(Context context, int status) {
        this.context = context;
        this.status = status;

        if (status == OrderStatus.STATUS_ORDERED) {
            orders = OrdersProvider.getUnFulfilledOrders();
        } else if (status == OrderStatus.STATUS_FULFILLED) {
            orders = OrdersProvider.getFilledOrders();
        } else if (status == OrderStatus.STATUS_CLOSED) {
            orders = OrdersProvider.getClosedOrders();
        }

        Log.i("TTT", "Orders Size: " + orders.size());
    }

    @Override
    public OrdersVH onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.orders_adapter_filled;
        switch (status) {
            case OrderStatus.STATUS_ORDERED:
                layout = R.layout.orders_adapter_unfullfilled;
                break;
            case OrderStatus.STATUS_CLOSED:
                layout = R.layout.orders_adapter_closed;
                break;
        }
        View view = LayoutInflater.from(context).inflate(layout, null);
        OrdersVH vh = new OrdersVH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(OrdersVH holder, int position) {
        Order order = orders.get(position);
        holder.tvOrderNumber.setText("#" + order.getId());
        holder.tvCustomerName.setText(order.getCustomer().getName());
        holder.tvScreenNumber.setText("Auditorium " + order.getScreenNumber());
        if (status == OrderStatus.STATUS_FULFILLED) {
            holder.btnAction.setText("Deliver");
        }

        if (status == OrderStatus.STATUS_ORDERED) {
            showOrderedItems(order, holder);
            showOrderTimes(order, holder);
        }

        if (listener != null) {
            holder.bind(order, listener);
        }
    }

    private void showOrderTimes(Order order, OrdersVH holder) {
        Calendar calendar = Calendar.getInstance();
        long elapsed = calendar.getTimeInMillis() - order.getCreatedAt().getTime();
        String elapsedTime = getElapsedTime(elapsed);
        holder.tvScreenNumber.setText(elapsedTime);
        long elapsedMinutes = elapsed / (60 * 1000);
        Log.i(TAG, "Time Diff: " + elapsedMinutes);
        if (elapsedMinutes <= TIME_DIFF_GREEN) {
            holder.tvScreenNumber.setTextColor(ContextCompat.getColor(context, R.color.colorLightGreen));
        } else if (elapsedMinutes <= TIME_DIFF_YELLOW) {
            holder.tvScreenNumber.setTextColor(ContextCompat.getColor(context, R.color.colorLightTeal));
        } else {
            holder.tvScreenNumber.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
    }

    private String getElapsedTime(long millis) {
        String format = String.format("%%0%dd", 2);
        millis = millis / 1000;
        String seconds = String.format(format, millis % 60);
        String minutes = String.format(format, millis / 60);
        return minutes + ":" + seconds;
    }

    private void showOrderedItems(Order order, OrdersVH holder) {
        int itemCount = 0;
        for (OrderItem item : order.getItems()) {
            for (int i = 0; i < item.getQuantity(); i++) {
                if (itemCount < 5) {
                    holder.ivs.get(itemCount).setVisibility(View.VISIBLE);
                    switch (item.getItemCode()) {
                        case "pizza":
                            holder.ivs.get(itemCount).setImageResource(R.drawable.pizza);
                            break;
                        case "burger":
                            holder.ivs.get(itemCount).setImageResource(R.drawable.burger);
                            break;
                        case "hotdog":
                            holder.ivs.get(itemCount).setImageResource(R.drawable.hotdog);
                            break;
                        case "fries":
                            holder.ivs.get(itemCount).setImageResource(R.drawable.fries);
                            break;
                        case "drink":
                            holder.ivs.get(itemCount).setImageResource(R.drawable.cold_drinks);
                            break;
                    }
                }
                itemCount++;
            }
        }
        if (itemCount < 6) {
            holder.tv6.setVisibility(GONE);
        } else {
            holder.tv6.setVisibility(View.VISIBLE);
        }
        if (itemCount < 5) {
            for (int i = 4; i > itemCount - 1; i--) {
                holder.ivs.get(i).setVisibility(GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOnOrderClickedListener(OnOrderClickListener listener) {
        this.listener = listener;
    }

    static class OrdersVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tvOrderNo)
        TextView tvOrderNumber;
        @BindView(R.id.tvCustomerName)
        TextView tvCustomerName;
        @BindView(R.id.tvScreenNo)
        TextView tvScreenNumber;

        // Filled Orders
        @Nullable
        @BindView(R.id.btnAction)
        Button btnAction;

        // UnFilled Orders
        @Nullable
        @BindViews({R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5})
        List<ImageView> ivs;
        @Nullable
        @BindView(R.id.tv6)
        TextView tv6;

        public OrdersVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Order order, final OnOrderClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onOrderClicked(order);
                }
            });
            if (btnAction != null) {
                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onOrderDeliverClicked(order);
                    }
                });
            }
        }

    }
}
