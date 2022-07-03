package com.revature.scramble.controller;

import java.util.List;

import com.revature.scramble.models.Session;
import com.revature.scramble.repository.entities.LoginInfo;
import com.revature.scramble.repository.entities.UserInfo;
import com.revature.scramble.service.AccountService;
import com.revature.scramble.service.ListingService;

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

    public static Handler fetch_get_all_username = ctx ->{
        //Check Session
        if(HomeController.check_account() && !Session.is_frozen){
            System.out.println("fetch_get_username");
            //LoginInfo login_obj = AccountService.get_login_info_from_user_id(user_id);
            List<LoginInfo> login_list = AccountService.get_all_login_info();
            String json_string = AccountService.get_json_string_from_login_list(login_list);
            ctx.json(json_string);
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            ctx.redirect("/logout");
        }
    };

    public static Handler fetch_post_moderate_account = ctx ->{
        //Check Session
        if(HomeController.check_account() && !Session.is_frozen){
            if(Integer.parseInt(ctx.formParam("user_id")) != -1 &&
            (ctx.formParam("is_frozen").equals("true") || ctx.formParam("is_frozen").equals("false"))){
                int user_id = Integer.parseInt(ctx.formParam("user_id"));
                boolean is_frozen = ctx.formParam("is_frozen").equals("true");
                System.out.println("fetch_post_moderate_account:\n\tis_frozen: " + is_frozen
                    +"\n\tuser_id: " +user_id);
                AccountService.moderate_user_account(user_id, is_frozen);
            }
        }else if(Session.is_frozen){
            ctx.redirect("/frozen");
        }
        else{
            ctx.redirect("/logout");
        }
    };

}
