package com.tapsfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tapsfoods.dao.RestaurantDAO;
import com.TapFoods.model.Restaurant;
import com.tappfoods.dbutil.DBUtils;

public class RestaurantDaoImpl implements RestaurantDAO {
    private static final String ADD_RESTAURANT = "INSERT INTO restaurant (restaurantName, deliveryTime, cuisineType, address, ratings, isActive) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_RESTAURANT = "SELECT * FROM restaurant";
    private static final String SELECT_ON_RESTAURANTID = "SELECT * FROM restaurant WHERE restaurantId = ?";
    private static final String UPDATE_ON_RESTAURANTID = "UPDATE restaurant SET restaurantName = ?, deliveryTime = ?, cuisineType = ?, address = ?, ratings = ?, isActive = ? WHERE restaurantId = ?";
    private static final String DELETE_ON_RESTAURANTID = "DELETE FROM restaurant WHERE restaurantId = ?";

    private Connection connection;
    private PreparedStatement pstmt;
    private int status;
    private Statement stmt;
    private ResultSet resultSet;

    private ArrayList<Restaurant> restaurantList = new ArrayList<>();

    public RestaurantDaoImpl() {
        connection = DBUtils.myConnect();
    }

    @Override
    public int addRestaurant(Restaurant r) {
        try {
            pstmt = connection.prepareStatement(ADD_RESTAURANT);
            pstmt.setString(1, r.getRestaurantName());
            pstmt.setInt(2, r.getDeliveryTime());
            pstmt.setString(3, r.getCuisineType());
            pstmt.setString(4, r.getAddress());
            pstmt.setFloat(5, r.getRatings());
            pstmt.setBoolean(6, r.isActive());

            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Restaurant> fetchAllRestaurant() {
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_RESTAURANT);
            restaurantList = extractRestaurantFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    private ArrayList<Restaurant> extractRestaurantFromResultSet(ResultSet resultSet) {
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        try {
            while (resultSet.next()) {
                restaurants.add(new Restaurant(
                        resultSet.getInt("restaurantId"),
                        resultSet.getString("restaurantName"),
                        resultSet.getInt("deliveryTime"),
                        resultSet.getString("cuisineType"),
                        resultSet.getString("address"),
                        resultSet.getFloat("ratings"),
                        resultSet.getBoolean("isActive"),
                        resultSet.getInt("adminId")
                       
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    @Override
    public Restaurant fetchSpecificRestaurant(int restaurantId) {
        Restaurant restaurant = null;
        try {
            pstmt = connection.prepareStatement(SELECT_ON_RESTAURANTID);
            pstmt.setInt(1, restaurantId);
            resultSet = pstmt.executeQuery();
            restaurantList = extractRestaurantFromResultSet(resultSet);
            if (!restaurantList.isEmpty()) {
                restaurant = restaurantList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public int updateRestaurant(Restaurant r) {
        try {
            pstmt = connection.prepareStatement(UPDATE_ON_RESTAURANTID);
            pstmt.setString(1, r.getRestaurantName());
            pstmt.setInt(2, r.getDeliveryTime());
            pstmt.setString(3, r.getCuisineType());
            pstmt.setString(4, r.getAddress());
            pstmt.setFloat(5, r.getRatings());
            pstmt.setBoolean(6, r.isActive());
            pstmt.setInt(7, r.getRestaurantId());

            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteRestaurant(int restaurantId) {
        try {
            pstmt = connection.prepareStatement(DELETE_ON_RESTAURANTID);
            pstmt.setInt(1, restaurantId);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
