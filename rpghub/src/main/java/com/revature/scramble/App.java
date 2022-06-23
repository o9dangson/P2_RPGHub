package com.revature.scramble;

import com.revature.scramble.controller.HomeController;
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
    }
    
}