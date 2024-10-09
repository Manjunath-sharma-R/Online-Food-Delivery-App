package com.tapsfoods.dao;

import java.util.List;
import com.TapFoods.model.OrderItem;

public interface OrderItemDAO {
    int addOrderItem(OrderItem oi);
    List<OrderItem> fetchAllOrders();
    List<OrderItem> fetchSpecificOrder(int orderId);
}
