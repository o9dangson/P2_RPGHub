package com.revature.scramble.controller;

import java.util.List;

import com.revature.scramble.service.EntryService;
import com.revature.scramble.service.ListingService;
import com.revature.scramble.models.Session;
import com.revature.scramble.repository.ListingDao;
import com.revature.scramble.repository.entities.Entry;
import com.revature.scramble.repository.entities.Listing;

import io.javalin.http.Handler;

public class EntryController {

    public static Handler fetch_post_create_entry = ctx ->{
        System.out.println("fetch_post_create_entry");
        if(HomeController.check_account() && !Session.is_frozen){
            //Log 
            //database request
            Entry new_entry = new Entry(-1,Integer.parseInt(ctx.formParam("list_id")), Integer.parseInt(ctx.formParam("user_id")), ctx.formParam("user_role"), ctx.formParam("user_note"),"Pending");
            System.out.println("new_entry");
            //call the service method here
            EntryService.create_new_entry(new_entry);
            System.out.println("fetch_post_create_entry: creation done");
            ctx.redirect("/listing/manage/"+ctx.formParam("list_id")+"/view");
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            //Render page
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_post_update_entry = ctx ->{
        if(HomeController.check_account() && !Session.is_frozen){
            System.out.println("fetch_post_update_entry: " + ctx.formParam("status"));
            if(Integer.parseInt(ctx.formParam("entry_id")) != -1 &&
                Integer.parseInt(ctx.formParam("list_id")) != -1){    
                //Log 
                //database request
                String user_status = ctx.formParam("status");
                int entry_id = Integer.parseInt(ctx.formParam("entry_id"));

                //Update particular entry 
                EntryService.update_entry(entry_id, user_status);
                //Update particular listing
                if(user_status.equals("Accepted")){
                    ListingService.update_listing_service(new Listing(Integer.parseInt(ctx.formParam("list_id")), Integer.parseInt(ctx.formParam("user_id")), ctx.formParam("list_name"), ctx.formParam("dungeonName"), Integer.parseInt(ctx.formParam("max_size")), Integer.parseInt(ctx.formParam("cur_size"))+1 ));
                }
                else if(user_status.equals("Rejected")){
                    ListingService.update_listing_service(new Listing(Integer.parseInt(ctx.formParam("list_id")), Integer.parseInt(ctx.formParam("user_id")), ctx.formParam("list_name"), ctx.formParam("dungeonName"), Integer.parseInt(ctx.formParam("max_size")), Integer.parseInt(ctx.formParam("cur_size"))));
                }
            }
            ctx.redirect("/view/"+ctx.formParam("list_id"));
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            //Render page
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_post_delete_entry = ctx ->{
        if(HomeController.check_account() && !Session.is_frozen){
            if(Integer.parseInt(ctx.formParam("entry_id")) != -1 && 
                Integer.parseInt(ctx.formParam("list_id")) != -1 &&
                Integer.parseInt(ctx.formParam("user_id")) != -1){
                //Log 
                //database request
                int entry_id = Integer.parseInt(ctx.formParam("entry_id"));
                int list_id = Integer.parseInt(ctx.formParam("list_id"));
                //Check if entry was previously accepted
                Entry entry_obj = EntryService.get_entry_by_entry_id(entry_id);
                if (entry_obj.getStatus().equals("Accepted")){
                    Listing listing_obj = ListingService.get_listing_by_list_id( list_id );
                    listing_obj.setCur_size( listing_obj.getCur_size() > 0 ? listing_obj.getCur_size() -1 : 0 );
                    ListingService.update_listing_service(listing_obj);
                }
                //Delete Entry
                EntryService.delete_entry(entry_id);
            }
            ctx.redirect("/view/"+ctx.formParam("list_id"));
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            //Render page
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_get_all_entries = ctx ->{
        if(HomeController.check_account() && !Session.is_frozen){
            //Log 
            //Render json of entries
            int list_id = Integer.parseInt(ctx.pathParam("list_id"));
            List<Entry> list_of_entry = EntryService.get_all_entries(list_id);
            String json_string = EntryService.get_json_string_from_entries(list_of_entry);
            ctx.json(json_string);
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log 
            //Render page
            ctx.redirect("/logout");
        }
    };
}
