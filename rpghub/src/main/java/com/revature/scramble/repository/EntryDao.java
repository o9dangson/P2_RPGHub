package com.revature.scramble.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.scramble.repository.entities.Entry;
import com.revature.scramble.repository.interfaces.EntryDaoInterface;
import com.revature.scramble.util.ConnectionFactory;

public class EntryDao implements EntryDaoInterface{

    @Override
    public int insert_entry(Entry entryEntity) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "INSERT INTO entry_table VALUES (default, ? , ? , ?, ?, ?) RETURNING entry_id;";
        int result_user_id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, entryEntity.getList_id());
            preparedStatement.setInt(2, entryEntity.getUser_id());
            preparedStatement.setString(3, entryEntity.getUser_role());
            preparedStatement.setString(4, entryEntity.getUser_note());
            preparedStatement.setString(5, entryEntity.getStatus());

            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                result_user_id = result.getInt(1);
            }
            return result_user_id;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return result_user_id;
        }
    }

    @Override
    public Entry select_entry_by_entry_id(int entry_id) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM entry_table WHERE entry_id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, entry_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return new Entry(
                    resultSet.getInt(1), 
                    resultSet.getInt(2), 
                    resultSet.getInt(3),
                    resultSet.getString(4), 
                    resultSet.getString(5), 
                    resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Entry(-1, -1, -1, "", "", "Pending");
    }

    @Override
    public List<Entry> select_entry_by_list_id(int list_id) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM entry_table WHERE list_id=?;";
        List<Entry> list_of_entry = new ArrayList<Entry>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, list_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Entry new_entry = new Entry(
                    resultSet.getInt(1), 
                    resultSet.getInt(2), 
                    resultSet.getInt(3),
                    resultSet.getString(4), 
                    resultSet.getString(5), 
                    resultSet.getString(6));
                list_of_entry.add(new_entry);
            }
            return list_of_entry;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_of_entry;
    }

    @Override
    public boolean update_entry_by_entry_id(int entry_id, String varName, String value) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "UPDATE entry_table SET ? = ? WHERE entry_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, varName);
            preparedStatement.setString(2, value);
            preparedStatement.setInt(3, entry_id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean remove_entry_by_entry_id(int entry_id) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "DELETE FROM entry_table WHERE entry_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, entry_id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
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
