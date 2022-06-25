package com.revature.scramble.controller;

import com.revature.scramble.models.Session;

import io.javalin.http.Handler;

public class HomeController {

    private static boolean check_account(){
        if (Session.user_id != -1){
            if (!Session.is_frozen)
                return true;
        }
        return false;
    }

    public static Handler homepageEntered = ctx -> {
        if(check_account()){
            //Logged in
            ctx.redirect("account.html");

        }else{
            //Not logged in
            ctx.redirect("index.html");
        }
    };
    
}
