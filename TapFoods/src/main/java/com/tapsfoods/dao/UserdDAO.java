package com.tapsfoods.dao;

import java.util.ArrayList;
import com.TapFoods.model.user;

public interface UserdDAO 
{
    int addUser(user u);
    ArrayList<user> getAllUser();
    user getUser(String email);
    int updateUser(user u);
    int deleteUser(String email);
  
}
