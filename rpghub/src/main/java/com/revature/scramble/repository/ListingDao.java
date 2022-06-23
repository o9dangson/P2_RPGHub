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
    public List<Listing> getAllListings() {
        Connection connection = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM listing_table;";
        List<Listing> groupListings = new ArrayList();

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
            return null;
        }
        
        
    }


	@Override
	public void insert_listing(Listing listing) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "INSERT INTO listing_table VALUES (default, ?,?,?,?,?);";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,listing.getGroupLeaderId());
            preparedStatement.setString(2,listing.getListName());
            preparedStatement.setString(3,listing.getDungeonName());
            preparedStatement.setInt(4,listing.getMax_size());
            preparedStatement.setInt(5,listing.getCur_size());

            preparedStatement.execute();
		}catch(SQLException e){
            e.printStackTrace();
        }
        
	}

	@Override
	public void delete_listing(Listing listing) {
		Connection connection = ConnectionFactory.getConnection();
        String sql = "DELETE FROM listing_table where listing_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,listing.getListingId());

            preparedStatement.execute();
		}catch(SQLException e){
            e.printStackTrace();
        }
		
	}
    
}
