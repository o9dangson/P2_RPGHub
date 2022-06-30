package com.revature.scramble.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.scramble.repository.entities.Listing;
import com.revature.scramble.repository.interfaces.ListingDaoInterface;
import com.revature.scramble.util.ConnectionFactory;

public class ListingDao implements ListingDaoInterface{

    @Override
    public List<Listing> select_all_listing() {
        Connection connection = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM listing_table;";
        List<Listing> groupListings = new ArrayList<Listing>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet results = preparedStatement.executeQuery();

            while(results.next()){
                groupListings.add(
                    new Listing(
                        results.getInt(1),
                        results.getInt(2),
                        results.getString(3),
                        results.getString(4),
                        results.getInt(5),
                        results.getInt(6)));
            }return groupListings;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return groupListings;
    }


    @Override
    public List<Listing> select_listing_by_user_id(int user_id) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM listing_table WHERE user_id = ?;";
        List<Listing> groupListings = new ArrayList<Listing>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            ResultSet results = preparedStatement.executeQuery();

            while(results.next()){
                groupListings.add(
                    new Listing(
                        results.getInt(1),
                        results.getInt(2),
                        results.getString(3),
                        results.getString(4),
                        results.getInt(5),
                        results.getInt(6)));
            }return groupListings;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return groupListings;
    }

    @Override
    public Listing select_listing_by_list_id(int list_id) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM listing_table WHERE list_id = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, list_id);
            ResultSet results = preparedStatement.executeQuery();

            while(results.next()){
                return new Listing(
                    results.getInt(1),
                    results.getInt(2),
                    results.getString(3),
                    results.getString(4),
                    results.getInt(5),
                    results.getInt(6));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return new Listing(-1, -1, "failed", "failed", 0, 0);
    }

	@Override
	public int insert_listing(Listing listing) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "INSERT INTO listing_table VALUES (default, ?,?,?,?,?) RETURNING list_id;";
        int result_list_id = 0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,listing.getUser_id());
            preparedStatement.setString(2,listing.getListName());
            preparedStatement.setString(3,listing.getDungeonName());
            preparedStatement.setInt(4,listing.getMax_size());
            preparedStatement.setInt(5,listing.getCur_size());

            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                result_list_id = result.getInt(1);
            }
            return result_list_id;
		}catch(SQLException e){
            e.printStackTrace();
        }
        return result_list_id;
	}

	@Override
	public void delete_listing(Listing listing) {
		Connection connection = ConnectionFactory.getConnection();
        String sql = "DELETE FROM listing_table where list_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,listing.getListingId());

            preparedStatement.execute();
		}catch(SQLException e){
            e.printStackTrace();
        }
		
	}
    
}
