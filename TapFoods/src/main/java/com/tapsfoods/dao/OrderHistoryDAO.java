package com.tapsfoods.dao;

import java.util.List;
import com.TapFoods.model.OrderHistory;

public interface OrderHistoryDAO {
    int addOrderHistory(OrderHistory oh);
    List<OrderHistory> getOrderHistory(int userId);
}
