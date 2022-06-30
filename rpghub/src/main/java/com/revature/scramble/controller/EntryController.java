package com.revature.scramble.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.scramble.service.EntryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.scramble.repository.entities.Entry;

import io.javalin.http.Handler;

public class EntryController {
    
    public static Handler get_listing_info_page = ctx ->{
        if(HomeController.check_account()){
            //Log 
            //Render page
            // try{
            //     Map<String,String> result = new ObjectMapper().readValue(ctx.body(), Map.class);
            // }catch(IOException e){
            //     e.printStackTrace();
            // }
            ctx.redirect("/listing.html");
        }
        else{
            //Log
            //Render page
            ctx.redirect("/index.html");
        }
    };

    public static Handler post_create_entry = ctx ->{
        
    };

    public static Handler post_update_entry = ctx ->{
        
    };

    public static Handler post_delete_entry = ctx ->{

    };

    public static Handler get_all_entries = ctx ->{
        if(HomeController.check_account()){
            //Log 
            //Render json of entries
            System.out.println("list_id: " + ctx.pathParam("list_id"));
            int list_id = Integer.parseInt(ctx.pathParam("list_id"));
            List<Entry> list_of_entry = EntryService.get_all_entries(list_id);
            ctx.json(EntryService.get_json_string_from_entries(list_of_entry));
        }
        else{
            //Log 
            //Render page
            ctx.redirect("/index.html");

        }
    };
}
