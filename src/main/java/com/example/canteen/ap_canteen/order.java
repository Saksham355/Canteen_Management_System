package com.example.canteen.ap_canteen;

import java.io.Serializable;
import java.util.ArrayList;

public class order implements Cloneable , Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private ArrayList<items> orderList;
    private String status;
    private boolean priority;
    public int count;
    private int total;

    public ArrayList<items> getOrderList() {
        return orderList;
    }
    public order(){}
    public order(String id) {
        this.id = id;
        orderList = new ArrayList<>();
        count = canteen.orders.size();
        if (canteen.id_to_customer.get(id) != null) {
            priority = canteen.id_to_customer.get(id).getVip();
        }
        setStatus("waiting");
    }

    public void add(String code) {
        items i = canteen.code_to_item.get(code);
        orderList.add(i);
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getPriority() {
        return priority;
    }

    public String getId() {
        return id;
    }

    public void setTotal() {
        int t = 0;
        for (items i : orderList) {
            t = t + i.getPrice() * i.getQuantity();
        }
        total = t;
    }

    public int getTotal() {
        setTotal();
        return total;
    }

    public order clone(order order) {
        order clonedOrder = new order(this.id);
        clonedOrder.status = this.status;
        clonedOrder.priority = this.priority;
        clonedOrder.count = this.count;
        clonedOrder.total = this.total;

        clonedOrder.orderList = new ArrayList<items>();
        clonedOrder.getOrderList().addAll(this.orderList);

        return clonedOrder;
    }
}
