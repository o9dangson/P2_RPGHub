package com.revature.scramble.repository.interfaces;

import java.util.List;

import com.revature.scramble.repository.entities.Listing;

public interface ListingDaoInterface {
    List<Listing> select_all_listing();
    List<Listing> select_listing_by_user_id(int user_id);
    Listing select_listing_by_list_id(int list_id);
    int insert_listing(Listing listing);
    void update_listing(Listing listing);
    void delete_listing(int list_id);
}