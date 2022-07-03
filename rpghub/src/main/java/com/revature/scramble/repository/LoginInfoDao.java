package com.revature.scramble.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.scramble.repository.entities.LoginInfo;
import com.revature.scramble.repository.interfaces.LoginInfoDaoInterface;
import com.revature.scramble.util.ConnectionFactory;

public class LoginInfoDao implements LoginInfoDaoInterface{

    @Override
    public LoginInfo selectUser(int id) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM user_login where user_id = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet results = preparedStatement.executeQuery();
            while(results.next()){
                return new LoginInfo(
                results.getInt(1), 
                results.getString(2), 
                results.getString(3)); 
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return new LoginInfo(-1, "failed", "failed");
    }

    @Override
    public LoginInfo selectUser(String username, String password) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM user_login where username = ? AND password = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet results = preparedStatement.executeQuery();
            while(results.next()){
                return new LoginInfo(
                results.getInt(1), 
                results.getString(2), 
                results.getString(3)); 
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return new LoginInfo(-1, "failed", "failed");
    }

    @Override
    public List<LoginInfo> selectAllUser() {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM user_login;";
        List<LoginInfo> login_list = new ArrayList<LoginInfo>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet results = preparedStatement.executeQuery();
            while(results.next()){
                login_list.add(new LoginInfo(
                    results.getInt(1), 
                    results.getString(2), 
                    results.getString(3)) ); 
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return login_list;
    }
    
}
