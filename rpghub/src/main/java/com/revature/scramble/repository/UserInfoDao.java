package com.revature.scramble.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.scramble.repository.entities.UserInfo;
import com.revature.scramble.repository.interfaces.UserInfoDaoInterface;
import com.revature.scramble.util.ConnectionFactory;


public class UserInfoDao implements UserInfoDaoInterface{

    @Override
    public UserInfo select_user_by_info_id(int info_id) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM user_info WHERE info_id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, info_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return new UserInfo(
                    resultSet.getInt(1), 
                    resultSet.getInt(2), 
                    resultSet.getString(3),
                    resultSet.getBoolean(4), 
                    resultSet.getBoolean(5));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new UserInfo(-1, -1, "failed", false, false);
    }

    @Override
    public UserInfo select_user_by_user_id(int user_id) {
       Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM user_info WHERE user_id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return new UserInfo(
                    resultSet.getInt(1), 
                    resultSet.getInt(2), 
                    resultSet.getString(3),
                    resultSet.getBoolean(4), 
                    resultSet.getBoolean(5));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new UserInfo(-1, -1, "failed", false, false);
    }

    @Override
    public UserInfo select_user_by_char_name(String char_name) {
       Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM user_info WHERE char_name=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, char_name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return new UserInfo(
                    resultSet.getInt(1), 
                    resultSet.getInt(2), 
                    resultSet.getString(3),
                    resultSet.getBoolean(4), 
                    resultSet.getBoolean(5));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new UserInfo(-1, -1, "failed", false, false);
    }

    @Override
    public boolean update_user_by_user_id(int user_id, boolean is_frozen) {
   Connection connection = ConnectionFactory.getConnection();
        String sql = "UPDATE user_info SET is_frozen = ? WHERE user_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setBoolean(1, is_frozen);
            preparedStatement.setInt(2, user_id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    
}
