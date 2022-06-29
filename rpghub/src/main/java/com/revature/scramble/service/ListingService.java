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

    public static List<Listing> get_listings_by_id(int id){
        return listingDao.select_listing_by_user_id(id);
    }

    public static String get_json_string_from_list(List<Listing> my_list){
        String json_string = "";
        int my_list_size = my_list.size();
        int counter = 0;
        //Code to convert to json
        json_string+="{\"my_list\":[";
        for(Listing listing:my_list){
            //String str_to_add = String.format("{\"list_id\":%d,\"user_id\":%d,\"list_name\":%s,\"dungeonName\":%s,\"max_size\":%d,\"cur_size\":%d}", listing.getListingId(), listing.getUser_id(), listing.getListName(), listing.getDungeonName(), listing.getMax_size(), listing.getCur_size());
            //json_string+= str_to_add;
            json_string+=new Gson().toJson(listing);
            counter++;
            if(counter != my_list_size)
                json_string+=",";
        }
        json_string+="]}";
        return json_string;
    }
}