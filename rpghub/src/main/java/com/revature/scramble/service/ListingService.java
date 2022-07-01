package com.revature.scramble.service;

import java.util.List;

import com.google.gson.Gson;
import com.revature.scramble.repository.ListingDao;
import com.revature.scramble.repository.entities.Listing;

public class ListingService {
    private static ListingDao listingDao = new ListingDao();
    
    public static List<Listing> get_all_listings(){
        List<Listing> listings = listingDao.select_all_listing();
        return listings;
    }

    public static List<Listing> get_listings_by_user_id(int id){
        return listingDao.select_listing_by_user_id(id);
    }

    public static Listing get_listing_by_list_id(int id){
        return listingDao.select_listing_by_list_id(id);
    }

    public static int create_listing(Listing listing){
        int new_list_id = listingDao.insert_listing(listing);
        return new_list_id;
    }

    public static void update_listing_service(Listing listing){
        listingDao.update_listing(listing);
    }

    public static void delete_listing_using_obj(Listing listing){
        listingDao.delete_listing(listing);
    }

    public static String get_json_string_from_list(List<Listing> my_list){
        String json_string = "";
        int my_list_size = my_list.size();
        int counter = 0;
        json_string+="{\"my_list\":[";
        for(Listing listing:my_list){
            json_string+=new Gson().toJson(listing);
            counter++;
            if(counter != my_list_size)
                json_string+=",";
        }
        json_string+="]}";
        return json_string;
    }

    public static String get_json_string_from_list_obj(Listing listing){
        return new Gson().toJson(listing);
    }
}