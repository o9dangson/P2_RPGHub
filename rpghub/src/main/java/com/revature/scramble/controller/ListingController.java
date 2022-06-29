package com.revature.scramble.controller;

import java.util.List;

import com.revature.scramble.models.Session;
import com.revature.scramble.repository.entities.Listing;
import com.revature.scramble.service.ListingService;

import io.javalin.http.Handler;

public class ListingController {
    
    public static Handler get_user_listings_by_id = ctx ->{
        //Check Session
        if(Session.user_id != -1){
            //Log somehow
            List<Listing> list_of_listings =  ListingService.get_listings_by_id(Session.user_id);
            String json_string = ListingService.get_json_string_from_list(list_of_listings);
            ctx.json(json_string);
        }
        else{
            SessionController.reset_session();
            ctx.redirect("/index.html");
        }
    };

    //This function is for the account page to fetch.
    //Basically supposed to be the same as above function
    public static Handler get_all_listings = ctx ->{
        //Check Session
        if(Session.user_id != -1){
            //Log somehow
            List<Listing> list_of_listings = ListingService.get_all_listings();
            String json_string = ListingService.get_json_string_from_list(list_of_listings);
            ctx.json(json_string);
        }
        else{
            SessionController.reset_session();
            ctx.redirect("/index.html");
        }
    };
}
