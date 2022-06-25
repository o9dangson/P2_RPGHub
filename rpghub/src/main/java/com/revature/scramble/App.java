package com.revature.scramble;

import com.revature.scramble.controller.AccountController;
import com.revature.scramble.controller.HomeController;
import com.revature.scramble.controller.SessionController;
import com.revature.scramble.models.Session;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import com.revature.scramble.repository.LoginInfoDao;
import com.revature.scramble.repository.entities.LoginInfo;

public class App 
{
    public static void main( String[] args )
    {
        Javalin app = Javalin.create(
            config -> {
                config.addStaticFiles("src/main/resources/public", Location.EXTERNAL);
            }
        );

        app.start(9090);

        app.get("/", HomeController.homepageEntered);
        app.get("/session", SessionController.get_session);
        app.post("/account", AccountController.post_account_page);
        app.get("/account", AccountController.get_logged_in_account_page);
        app.get("/logout", HomeController.logout);
    }
    
}