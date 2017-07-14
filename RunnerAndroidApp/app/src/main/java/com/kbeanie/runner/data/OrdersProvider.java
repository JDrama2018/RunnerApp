package com.kbeanie.runner.data;

import com.kbeanie.runner.entity.Customer;
import com.kbeanie.runner.entity.Order;
import com.kbeanie.runner.entity.OrderItem;
import com.kbeanie.runner.entity.OrderStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vidushi on 20/6/17.
 */

public class OrdersProvider {
    public static List<Order> getUnFulfilledOrders() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 9);
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setCreatedAt(calendar.getTime());
        order.setId(12335);
        Customer customer = new Customer();
        customer.setId(3666567);
        customer.setName("Daniel M.");
        order.setCustomer(customer);
        order.setStatus(OrderStatus.STATUS_ORDERED);
        order.setScreenNumber(1);
        orders.add(order);

        List<OrderItem> items = new ArrayList<>();
        OrderItem item01 = new OrderItem();
        item01.setItemCode("pizza");
        item01.setItemTitle("Pizza");
        item01.setQuantity(1);

        items.add(item01);

        OrderItem item02 = new OrderItem();
        item02.setItemCode("burger");
        item02.setItemTitle("Burger");
        item02.setQuantity(3);


        items.add(item02);
        order.setItems(items);


        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 5);
        Order order2 = new Order();
        order2.setCreatedAt(calendar.getTime());
        Customer customer2 = new Customer();
        customer2.setId(3666567);
        order2.setId(2435232);
        customer2.setName("Jack S.");
        order2.setCustomer(customer2);
        order2.setStatus(OrderStatus.STATUS_ORDERED);
        order2.setScreenNumber(1);
        List<OrderItem> items2 = new ArrayList<>();
        OrderItem item10 = new OrderItem();
        item10.setItemCode("hotdog");
        item10.setQuantity(2);
        items2.add(item10);
        OrderItem item11 = new OrderItem();
        item11.setItemCode("drink");
        item11.setQuantity(1);
        items2.add(item11);
        order2.setItems(items2);
        orders.add(order2);
        return orders;
    }

    public static List<Order> getFilledOrders() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(4355);
        Customer customer = new Customer();
        customer.setId(3666567);
        customer.setName("Daniel M.");
        order.setCustomer(customer);
        order.setScreenNumber(1);
        order.setStatus(OrderStatus.STATUS_FULFILLED);
        orders.add(order);
        return orders;
    }

    public static List<Order> getClosedOrders() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(98645);
        Customer customer = new Customer();
        customer.setId(3666567);
        customer.setName("Daniel M.");
        order.setCustomer(customer);
        order.setScreenNumber(1);
        order.setStatus(OrderStatus.STATUS_CLOSED);
        orders.add(order);
        return orders;
    }

    public static List<OrderItem> getSampleItems(){
        List<OrderItem> items = new ArrayList<>();
        OrderItem item01 = new OrderItem();
        item01.setItemCode("pizza");
        item01.setItemTitle("Large pepperoni pizza");
        item01.setQuantity(1);

        items.add(item01);

        OrderItem item02 = new OrderItem();
        item02.setItemCode("drink");
        item02.setItemTitle("Large diet coke");
        item02.setQuantity(3);


        items.add(item02);
        return items;
    }

    public static List<String> getSampleDefaultMessages(){
        List<String> messages = new ArrayList<>();
        messages.add("Lorem ipsum dolor sit amet, consectetur adispiscing elit. Ergo hoc.");
        messages.add("Que Manulium ab iisque M.");
        messages.add("Que Manulium ab iisque M.");
        messages.add("Lorem ipsum dolor sit amet, consectetur adispiscing elit. Ergo hoc.");
        messages.add("Lorem ipsum dolor sit amet, consectetur adispiscing elit. Ergo hoc.");
        messages.add("Customized message.");
        return  messages;
    }

    public static List<String> getSampleMessages(){
        List<String> messages = new ArrayList<>();
        messages.add("Lorem ipsum dolor sit amet, consectetur adispiscing elit. Ergo hoc.");
        return messages;
    }
}
