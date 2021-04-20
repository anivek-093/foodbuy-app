package com.example.dashboard.data;

import com.example.dashboard.model.order.OrderDetails;
import com.example.dashboard.model.order.OrderResponse;

public class SharedOrderData {
    private static SharedOrderData instance;

    private OrderDetails sharedOrder;

    private SharedOrderData() {
        sharedOrder = null;
    }

    public static SharedOrderData getInstance() {
        if(instance == null) {
            instance = new SharedOrderData();
        }
        return instance;
    }

    public void saveOrder(OrderDetails order) {
        sharedOrder = order;
    }

    public OrderDetails getOrder() {
        return sharedOrder;
    }
}
