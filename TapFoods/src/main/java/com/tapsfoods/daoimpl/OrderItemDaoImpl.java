package com.tapsfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tapsfoods.dao.OrderItemDAO;
import com.TapFoods.model.OrderItem;
import com.tappfoods.dbutil.DBUtils;

public class OrderItemDaoImpl implements OrderItemDAO {

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    private static final String ADD_ORDERITEM = "INSERT INTO orderitem (orderId, menuId, quantity, subTotal) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDERITEMS = "SELECT * FROM orderitem";
    private static final String SELECT_SPECIFIC_ORDERITEM = "SELECT * FROM orderitem WHERE orderId = ?";

    public OrderItemDaoImpl() {
        connection = DBUtils.myConnect();
    }

    @Override
    public int addOrderItem(OrderItem oi) {
        int status = 0; // Initialize status
        try {
            pstmt = connection.prepareStatement(ADD_ORDERITEM);
            pstmt.setInt(1, oi.getOrderId());
            pstmt.setInt(2, oi.getMenuId());
            pstmt.setInt(3, oi.getQuantity());
            pstmt.setDouble(4, oi.getSubTotal());

            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(); // Ensure resources are closed after use
        }
        return status;
    }

    @Override
    public List<OrderItem> fetchAllOrders() {
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement(SELECT_ALL_ORDERITEMS);
            resultSet = pstmt.executeQuery();
            orderItemList = extractOrderItemsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(); // Ensure resources are closed after use
        }
        return orderItemList;
    }

    @Override
    public List<OrderItem> fetchSpecificOrder(int orderId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement(SELECT_SPECIFIC_ORDERITEM);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            orderItemList = extractOrderItemsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(); // Ensure resources are closed after use
        }
        return orderItemList;
    }

    private List<OrderItem> extractOrderItemsFromResultSet(ResultSet resultSet) {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderItems.add(new OrderItem(
                        resultSet.getInt("orderItemId"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("menuId"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("subTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    private void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (pstmt != null) pstmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
