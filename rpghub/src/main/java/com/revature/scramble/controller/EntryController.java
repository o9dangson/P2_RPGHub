package com.revature.scramble.controller;

import java.util.List;

import com.revature.scramble.service.EntryService;
import com.revature.scramble.service.ListingService;
import com.revature.scramble.repository.ListingDao;
import com.revature.scramble.repository.entities.Entry;
import com.revature.scramble.repository.entities.Listing;

import io.javalin.http.Handler;

public class EntryController {

    public static Handler fetch_post_create_entry = ctx ->{
        if(HomeController.check_account()){
            //Log 
            //database request
            int entry_id = Integer.parseInt(ctx.formParam("entry_id"));
            int list_id = Integer.parseInt(ctx.formParam("list_id"));
            int user_id = Integer.parseInt(ctx.formParam("user_id"));
            String user_role = ctx.formParam("user_role");
            String user_note = ctx.formParam("user_note");
            String user_status = ctx.formParam("status");
            Entry new_entry = new Entry(entry_id,list_id,user_id,user_role,user_note,user_status);

            //call the service method here
            EntryService.create_new_entry(new_entry);
            //Entry_Service.create_new_entry()
        }
        else{
            //Log
            //Render page
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_post_update_entry = ctx ->{
        if(HomeController.check_account()){
            //Log 
            //database request
            String user_status = ctx.formParam("status");
            int entry_id = Integer.parseInt(ctx.formParam("entry_id"));

            //Update particular entry 
            EntryService.update_entry(entry_id, user_status);
            //Update particular listing
            Listing new_listing = new Listing(Integer.parseInt(ctx.formParam("list_id")), Integer.parseInt(ctx.formParam("user_id")), ctx.formParam("list_name"), ctx.formParam("dungeonName"), Integer.parseInt(ctx.formParam("max_size")), Integer.parseInt(ctx.formParam("cur_size")));
            ListingService.update_listing_service(new_listing);
        }
        else{
            //Log
            //Render page
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_post_delete_entry = ctx ->{
        if(HomeController.check_account()){
            //Log 
            //database request
            int entry_id = Integer.parseInt(ctx.formParam("entry_id"));
            EntryService.delete_entry(entry_id);
        }
        else{
            //Log
            //Render page
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_get_all_entries = ctx ->{
        if(HomeController.check_account()){
            //Log 
            //Render json of entries
            int list_id = Integer.parseInt(ctx.pathParam("list_id"));
            List<Entry> list_of_entry = EntryService.get_all_entries(list_id);
            String json_string = EntryService.get_json_string_from_entries(list_of_entry);
            ctx.json(json_string);
        }
        else{
            //Log 
            //Render page
            ctx.redirect("/logout");
        }
    };
}
