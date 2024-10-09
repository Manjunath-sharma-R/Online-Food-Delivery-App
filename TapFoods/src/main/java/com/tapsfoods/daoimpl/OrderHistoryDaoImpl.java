package com.tapsfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tapsfoods.dao.OrderHistoryDAO;
import com.TapFoods.model.OrderHistory;
import com.tappfoods.dbutil.DBUtils;

public class OrderHistoryDaoImpl implements OrderHistoryDAO {
    private static final String ADD_ORDER_HISTORY = "INSERT INTO orderhistory (orderId, userId, totalAmount, status) VALUES (?, ?, ?, ?)";
    private static final String SELECT_SPECIFIC_ORDER_HISTORY = "SELECT * FROM orderhistory WHERE userId = ?";
    
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    
    public OrderHistoryDaoImpl() {
        connection = DBUtils.myConnect();
    }

    @Override
    public int addOrderHistory(OrderHistory oh) {
        int status = 0;
        try {
            pstmt = connection.prepareStatement(ADD_ORDER_HISTORY);
            pstmt.setInt(1, oh.getOrderId());
            pstmt.setInt(2, oh.getUserId());
            pstmt.setDouble(3, oh.getTotalAmount());
            pstmt.setString(4, oh.getStatus());
            
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<OrderHistory> getOrderHistory(int userId) {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement(SELECT_SPECIFIC_ORDER_HISTORY);
            pstmt.setInt(1, userId);
            
            resultSet = pstmt.executeQuery();
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    private List<OrderHistory> extractOrderHistoryFromResultSet(ResultSet resultSet) {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderHistoryList.add(new OrderHistory(
                    resultSet.getInt("orderHistoryId"),
                    resultSet.getInt("orderId"),
                    resultSet.getInt("userId"),
                    resultSet.getString("orderDate"),
                    resultSet.getDouble("totalAmount"),
                    resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}
