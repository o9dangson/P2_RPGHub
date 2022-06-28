package com.revature.scramble.controller;

import io.javalin.http.Handler;

public class EntryController {
    
    public static Handler get_listing_info_page = ctx ->{
        if(HomeController.check_account()){
            //Log 
            //Render page
            ctx.redirect("/listing.html");
        }
        else{
            //Log
            //Render page
            ctx.redirect("/index.html");
        }
    };

    public static Handler post_create_update_entry = ctx ->{
        
    };
}
