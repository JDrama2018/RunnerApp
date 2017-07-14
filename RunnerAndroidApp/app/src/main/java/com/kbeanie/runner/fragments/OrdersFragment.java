package com.kbeanie.runner.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kbeanie.runner.OrderDetailsActivity;
import com.kbeanie.runner.R;
import com.kbeanie.runner.RunnerActivity;
import com.kbeanie.runner.RunnerApplication;
import com.kbeanie.runner.adapters.OnOrderClickListener;
import com.kbeanie.runner.adapters.OrdersAdapter;
import com.kbeanie.runner.entity.Order;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vidushi on 20/6/17.
 */

public class OrdersFragment extends Fragment implements OnOrderClickListener{

    private final static String TAG = OrdersFragment.class.getSimpleName();

    @BindView(R.id.rvOrders)
    RecyclerView rvOrders;

    private int status;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            status = getArguments().getInt("status");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, null);
        ButterKnife.bind(this, view);


        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new OrdersDividerItemDecoration(rvOrders.getContext(),
                lm.getOrientation());
        rvOrders.addItemDecoration(dividerItemDecoration);
        rvOrders.setLayoutManager(lm);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        OrdersAdapter adapter = new OrdersAdapter(getActivity(), status);
        rvOrders.setAdapter(adapter);

        adapter.setOnOrderClickedListener(this);
    }

    @Override
    public void onOrderClicked(Order order) {
        Log.i(TAG, "Order Clicked: " + order.getCustomer().getName());
        Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
        startActivity(intent);
        RunnerApplication.selectedOrder = order;
    }

    @Override
    public void onOrderDeliverClicked(Order order) {
        ((RunnerActivity)getActivity()).showNotification("Hey "+ order.getCustomer().getName() + ", your food is coming. Please come to the steps down below.");
    }

    class OrdersDividerItemDecoration extends DividerItemDecoration{

        private Drawable divider;

        /**
         * Creates a divider {@link RecyclerView.ItemDecoration} that can be used with a
         * {@link LinearLayoutManager}.
         *
         * @param context     Current context, it will be used to access resources.
         * @param orientation Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
         */
        public OrdersDividerItemDecoration(Context context, int orientation) {
            super(context, orientation);
            divider = ContextCompat.getDrawable(context, R.drawable.rv_divider);

        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }
}
