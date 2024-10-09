package com.tapsfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tapsfoods.dao.MenuDAO;
import com.TapFoods.model.Menu;
import com.tappfoods.dbutil.DBUtils;

public class MenuDaoImpl implements MenuDAO {
    private Connection connection;
    private PreparedStatement pstmt;
    private int status;
    private Statement stmt;
    private ResultSet resultSet;
    private Menu menu;

    private static final String ADD_MENU_ITEM = "INSERT INTO menu (restaurantId, itemName, price, description, isAvailable) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_MENU = "SELECT * FROM menu";
    private static final String SELECT_MENU = "SELECT * FROM menu WHERE menuId = ?";
    private static final String SELECT_SPECIFIC_MENU = "SELECT * FROM menu WHERE restaurantId = ?";
    private static final String UPDATE_SPECIFIC_MENU = "UPDATE menu SET restaurantId = ?, itemName = ?, price = ?, description = ?, isAvailable = ? WHERE menuId = ?";
    private static final String DELETE_MENU = "DELETE FROM menu WHERE menuId = ? AND restaurantId = ?";

    public MenuDaoImpl() {
        connection = DBUtils.myConnect();
    }

    @Override
    public int addMenuItem(Menu m) {
        try {
            pstmt = connection.prepareStatement(ADD_MENU_ITEM);
            pstmt.setInt(1, m.getRestaurantId());
            pstmt.setString(2, m.getItemName());
            pstmt.setFloat(3, m.getPrice());
            pstmt.setString(4, m.getDescription());
            pstmt.setBoolean(5, m.isAvailable());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Menu> fetchAllMenu() {
        List<Menu> menuList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL_MENU);
            menuList = extractAllMenuFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }

    private List<Menu> extractAllMenuFromResultSet(ResultSet resultSet) {
        List<Menu> menuList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                menuList.add(new Menu(
                    resultSet.getInt("menuId"),
                    resultSet.getInt("restaurantId"),
                    resultSet.getString("itemName"),
                    resultSet.getFloat("price"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("isAvailable"),
                    resultSet.getString("imgPath")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public List<Menu> fetchSpecificMenuItem(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement(SELECT_SPECIFIC_MENU);
            pstmt.setInt(1, restaurantId);
            resultSet = pstmt.executeQuery();
            menuList = extractAllMenuFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public int updateMenuItem(Menu m) {
        try {
            pstmt = connection.prepareStatement(UPDATE_SPECIFIC_MENU);
            pstmt.setInt(1, m.getRestaurantId());
            pstmt.setString(2, m.getItemName());
            pstmt.setFloat(3, m.getPrice());
            pstmt.setString(4, m.getDescription());
            pstmt.setBoolean(5, m.isAvailable());
            pstmt.setInt(6, m.getMenuId());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteMenuItem(int menuId, int restaurantId) {
        try {
            pstmt = connection.prepareStatement(DELETE_MENU);
            pstmt.setInt(1, menuId);
            pstmt.setInt(2, restaurantId);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Menu getMenuItem(int menuId) {
        Menu menu = null;
        try {
            pstmt = connection.prepareStatement(SELECT_MENU);
            pstmt.setInt(1, menuId);
            resultSet = pstmt.executeQuery();
            List<Menu> menuList = extractAllMenuFromResultSet(resultSet);
            if (!menuList.isEmpty()) {
                menu = menuList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }
}
