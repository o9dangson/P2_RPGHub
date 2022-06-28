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
            ctx.redirect("/account.html");

        }else{
            //Not logged in
            SessionController.reset_session();
            ctx.redirect("/index.html");
        }
    };

    public static Handler logout = ctx ->{
        SessionController.reset_session();
        System.out.println("LOGGING OUT");
        ctx.redirect("/index.html");
    };
    
}
