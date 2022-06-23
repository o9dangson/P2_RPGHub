package com.revature.scramble.repository.interfaces;

import java.util.List;

import com.revature.scramble.repository.entities.Listing;

public interface ListingDaoInterface {
    List<Listing> getAllListings();
    void insert_listing(Listing listing);
    void delete_listing(Listing listing);
}