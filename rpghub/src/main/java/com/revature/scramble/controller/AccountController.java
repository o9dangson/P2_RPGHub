package com.revature.scramble.controller;

import com.revature.scramble.models.Session;
import com.revature.scramble.repository.entities.LoginInfo;
import com.revature.scramble.repository.entities.UserInfo;
import com.revature.scramble.service.AccountService;

import io.javalin.http.Handler;

public class AccountController {

    public static Handler post_account_page = ctx -> {
        //Verify Login details
        LoginInfo login_obj = AccountService.verify_details(ctx.formParam("username"), ctx.formParam("password"));
        //If true, update Session
        if (login_obj.getUserId() != -1 && !Session.is_frozen){
            UserInfo user_obj = AccountService.get_user_details(login_obj.getUserId());
            SessionController.update_session(user_obj);
            if(Session.is_frozen)
                ctx.redirect("/frozen");
            else
                ctx.render("/templates/account.vm");
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            //Log
            //Redirect to login
            ctx.redirect("/logout");
        }
    };

    public static Handler get_logged_in_account_page = ctx ->{
        //Check Session
        if(HomeController.check_account() && !Session.is_frozen){
            ctx.render("/templates/account.vm");
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            ctx.redirect("/logout");
        }
    };
}
