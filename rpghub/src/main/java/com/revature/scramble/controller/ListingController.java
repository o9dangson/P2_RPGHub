package com.revature.scramble.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.scramble.models.Session;
import com.revature.scramble.repository.entities.Listing;
import com.revature.scramble.service.ListingService;

import io.javalin.http.Handler;

public class ListingController {

    public static Handler get_listing_info_page = ctx ->{
        if(HomeController.check_account() && !Session.is_frozen){
            if(Integer.parseInt(ctx.pathParam("list_id")) != -1){
                //Log 
                //Render page
                Map<String,Integer> temp = new HashMap<>();
                temp.put("list_id", Integer.parseInt(ctx.pathParam("list_id")));
                ctx.render("/templates/listing.vm", temp);
            }else{
                //Log
                ctx.redirect("/account");
            }
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            ctx.redirect("/logout");
        }
    };

    public static Handler post_listing_info_page = ctx ->{
        if(HomeController.check_account() && !Session.is_frozen){
            System.out.println("post_listing_info_page: attempt\n\tlist_id: "+ ctx.formParam("list_id"));
            if(Integer.parseInt(ctx.formParam("list_id")) != -1){
                //Log 
                //Render page
                Map<String,Integer> temp = new HashMap<>();
                temp.put("list_id", Integer.parseInt(ctx.formParam("list_id")));
                ctx.render("/templates/listing.vm", temp);
            }else{
                //Log
                ctx.redirect("/account");
            }
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_get_user_listings_by_id = ctx ->{
        //Check Session
        if(HomeController.check_account() && !Session.is_frozen){
            //Log somehow
            List<Listing> list_of_listings =  ListingService.get_listings_by_user_id(Session.user_id);
            String json_string = ListingService.get_json_string_from_list(list_of_listings);
            ctx.json(json_string);
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_get_all_listings = ctx ->{
        //Check Session
        if(HomeController.check_account() && !Session.is_frozen){
            //Log somehow
            List<Listing> list_of_listings = ListingService.get_all_listings();
            String json_string = ListingService.get_json_string_from_list(list_of_listings);
            ctx.json(json_string);
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_get_particular_listing = ctx ->{
        if(HomeController.check_account() && !Session.is_frozen){
            Listing listing_obj = ListingService.get_listing_by_list_id(Integer.parseInt(ctx.pathParam("list_id")));
            String json_string = ListingService.get_json_string_from_list_obj(listing_obj);
            ctx.json(json_string);
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_post_create_listing = ctx->{
        if(HomeController.check_account() && !Session.is_frozen){
            Listing new_listing = new Listing(-1, Session.user_id, ctx.formParam("list_name"), ctx.formParam("dungeonName"), Integer.parseInt(ctx.formParam("max_size")), 0);
            int result_list_id = ListingService.create_listing(new_listing);
            System.out.println("fetch_post_create_listing: " + result_list_id);
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_post_update_listing = ctx->{
        if(HomeController.check_account() && !Session.is_frozen){
            Listing new_listing = new Listing(Integer.parseInt(ctx.formParam("list_id")), Session.user_id, ctx.formParam("list_name"), ctx.formParam("dungeonName"), Integer.parseInt(ctx.formParam("max_size")), Integer.parseInt(ctx.formParam("cur_size")));
            ListingService.update_listing_service(new_listing);
            ctx.redirect("/listing/"+ ctx.formParam("list_id"));
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }else{
            //log
            ctx.redirect("/logout");
        }
    };
    
    public static Handler fetch_post_delete_listing = ctx->{
        if(HomeController.check_account() && !Session.is_frozen){
            System.out.println("fetch_post_delete_listing: list_id= " + ctx.formParam("list_id"));
            if(Integer.parseInt(ctx.formParam("list_id")) != -1 &&
                (Integer.parseInt(ctx.formParam("user_user_id")) 
                == Integer.parseInt(ctx.formParam("list_user_id"))
                    || Session.is_mod) ){
                ListingService.delete_listing_using_obj(Integer.parseInt(ctx.formParam("list_id")));
                System.out.println("fetch_post_delete_listing: Attempted");
            }
            //Log
            ctx.redirect("/account");
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            ctx.redirect("/logout");
        }
    };
}
