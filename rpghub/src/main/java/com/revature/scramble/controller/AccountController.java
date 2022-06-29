package com.revature.scramble.controller;


import com.revature.scramble.models.Session;
import com.revature.scramble.repository.entities.LoginInfo;
import com.revature.scramble.repository.entities.UserInfo;
import com.revature.scramble.service.AccountService;

import io.javalin.http.Handler;

public class AccountController {

    public static Handler post_account_page = ctx -> {
        //Verify Login details
        System.out.println(ctx.formParam("username") + "\t" + ctx.formParam("password"));
        LoginInfo login_obj = AccountService.verify_details(ctx.formParam("username"), ctx.formParam("password"));
        //If true, update Session
        if (login_obj.getUserId() != -1){
            UserInfo user_obj = AccountService.get_user_details(login_obj.getUserId());
            SessionController.update_session(user_obj);
            //Log 
            //Render page
            ctx.redirect("/account.html");
        }
        else{
            //Log
            //Redirect to login
            SessionController.reset_session();
            ctx.redirect("/index.html");
        }
    };

    public static Handler get_logged_in_account_page = ctx ->{
        //Check Session
        if(Session.user_id != -1){
            ctx.redirect("/account.html");
        }
        else{
            SessionController.reset_session();
            ctx.redirect("/index.html");
        }
    };
}
