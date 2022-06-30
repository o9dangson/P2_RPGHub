package com.revature.scramble.controller;

import java.util.List;

import com.revature.scramble.service.EntryService;
import com.revature.scramble.repository.entities.Entry;

import io.javalin.http.Handler;

public class EntryController {

    public static Handler fetch_post_create_entry = ctx ->{
        if(HomeController.check_account()){
            //Log 
            //database request
            
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
            ctx.json(EntryService.get_json_string_from_entries(list_of_entry));
        }
        else{
            //Log 
            //Render page
            ctx.redirect("/logout");
        }
    };
}
