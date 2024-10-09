package com.tapsfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tapsfoods.dao.OrderTableDAO;
import com.TapFoods.model.OrderTable;
import com.tappfoods.dbutil.DBUtils;

public class OrderTableDAOImpl implements OrderTableDAO {
    private Connection connection;
    private PreparedStatement pstmt;
    private int status;
    private Statement stmt;
    private ResultSet resultSet;

    private ArrayList<OrderTable> orderTableList = new ArrayList<>();

    private static final String ADD_ORDER = "INSERT INTO ordertable (restaurantId, userId, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM ordertable";
    private static final String SELECT_SPECIFIC_ORDER = "SELECT * FROM ordertable WHERE restaurantId = ? AND userId = ?";
    private static final String GET_MAX_ORDER_ID = "SELECT MAX(orderId) FROM ordertable";

    public OrderTableDAOImpl() {
        connection = DBUtils.myConnect();
    }

    @Override
    public int addOrder(OrderTable o) {
        try {
            pstmt = connection.prepareStatement(ADD_ORDER);
            pstmt.setInt(1, o.getRestaurantId());
            pstmt.setInt(2, o.getUserId());
            pstmt.setDouble(3, o.getTotalAmount());
            pstmt.setString(4, o.getStatus());
            pstmt.setString(5, o.getPaymentMode());

            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<OrderTable> fetchAllOrders() {
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_ORDERS);
            orderTableList = extractOrdersFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderTableList;
    }

    private ArrayList<OrderTable> extractOrdersFromResultSet(ResultSet resultSet) {
        ArrayList<OrderTable> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orders.add(new OrderTable(
                        resultSet.getInt("orderId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getInt("userId"),
                        resultSet.getString("orderDate"), // Assuming this field exists
                        resultSet.getDouble("totalAmount"),
                        resultSet.getString("status"),
                        resultSet.getString("paymentMode")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public OrderTable fetchSpecificOrder(int restaurantId, int userId) {
        OrderTable orderTable = null;
        try {
            pstmt = connection.prepareStatement(SELECT_SPECIFIC_ORDER);
            pstmt.setInt(1, restaurantId);
            pstmt.setInt(2, userId);
            resultSet = pstmt.executeQuery();
            orderTableList = extractOrdersFromResultSet(resultSet);
            if (!orderTableList.isEmpty()) {
                orderTable = orderTableList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderTable;
    }

    @Override
    public int getLatestOrderId() {
        int maxId = 0;
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(GET_MAX_ORDER_ID);
            if (resultSet.next()) {
                maxId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }
}
