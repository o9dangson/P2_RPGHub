package com.revature.scramble.controller;

import com.revature.scramble.models.Session;

import io.javalin.http.Handler;

public class HomeController {

    public static boolean check_account(){
        if (Session.user_id != -1){
            if (!Session.is_frozen)
                return true;
        }
        return false;
    }

    public static Handler homepageEntered = ctx -> {
        if(check_account()){
            //Logged in
            ctx.render("/templates/account.vm");

        }
        else{
            //Not logged in
            SessionController.reset_session();
            ctx.render("/templates/index.vm");
        }
    };

    public static Handler logout = ctx ->{
        SessionController.reset_session();
        ctx.redirect("/");
    };

    public static Handler frozen_page = ctx ->{
        ctx.render("/templates/frozen.vm");
    };
    
}
