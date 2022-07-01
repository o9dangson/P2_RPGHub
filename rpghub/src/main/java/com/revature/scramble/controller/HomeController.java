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
<<<<<<< HEAD
            ctx.redirect("/account.html");
=======
            ctx.render("/templates/account.vm");
>>>>>>> listingpage_backup

        }else{
            //Not logged in
            SessionController.reset_session();
<<<<<<< HEAD
            ctx.redirect("/index.html");
=======
            ctx.render("/templates/index.vm");
>>>>>>> listingpage_backup
        }
    };

    public static Handler logout = ctx ->{
        SessionController.reset_session();
<<<<<<< HEAD
        System.out.println("LOGGING OUT");
        ctx.redirect("/index.html");
=======
        ctx.redirect("/");
>>>>>>> listingpage_backup
    };
    
}
