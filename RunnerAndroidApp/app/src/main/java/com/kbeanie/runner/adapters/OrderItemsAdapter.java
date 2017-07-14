package com.kbeanie.runner.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kbeanie.runner.R;
import com.kbeanie.runner.entity.OrderItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vidushi on 21/6/17.
 */

public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.OrderItemsVH> {

    private Context context;
    private List<OrderItem> items;
    public OrderItemsAdapter(Context context, List<OrderItem> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public OrderItemsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_items_adapter, parent, false);
        OrderItemsVH holder = new OrderItemsVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderItemsVH holder, int position) {
        OrderItem item = items.get(position);
        holder.tvItemTitle.setText(item.getItemTitle());
        holder.tvQuantity.setText("x " + item.getQuantity());
        switch (item.getItemCode()) {
            case "pizza":
                holder.ivItem.setImageResource(R.drawable.pizza);
                break;
            case "burger":
                holder.ivItem.setImageResource(R.drawable.burger);
                break;
            case "hotdog":
                holder.ivItem.setImageResource(R.drawable.hotdog);
                break;
            case "fries":
                holder.ivItem.setImageResource(R.drawable.fries);
                break;
            case "drink":
                holder.ivItem.setImageResource(R.drawable.cold_drinks);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class OrderItemsVH extends RecyclerView.ViewHolder{
        @BindView(R.id.ivItem)
        ImageView ivItem;
        @BindView(R.id.tvItemTitle)
        TextView tvItemTitle;
        @BindView(R.id.tvQuantity)
        TextView tvQuantity;
        public OrderItemsVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
