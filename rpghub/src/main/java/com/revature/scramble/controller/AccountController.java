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
        if (login_obj.getUserId() != -1){
            UserInfo user_obj = AccountService.get_user_details(login_obj.getUserId());
            SessionController.update_session(user_obj);
            //Log 
            //Render page
            ctx.render("/templates/account.vm");
        }
        else{
            //Log
            //Redirect to login
            ctx.redirect("/logout");
        }
    };

    public static Handler get_logged_in_account_page = ctx ->{
        //Check Session
        if(HomeController.check_account()){
            ctx.render("/templates/account.vm");
        }
        else{
            ctx.redirect("/logout");
        }
    };
}
