package com.tapsfoods.dao;

import java.util.List;

import com.TapFoods.model.OrderTable;

public interface OrderTableDAO {
    int addOrder(OrderTable o);
    List<OrderTable> fetchAllOrders();
    OrderTable fetchSpecificOrder(int restaurantId, int userId);
    int getLatestOrderId();
}
