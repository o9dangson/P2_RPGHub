package com.revature.scramble;

import com.revature.scramble.controller.AccountController;
import com.revature.scramble.controller.EntryController;
import com.revature.scramble.controller.HomeController;
import com.revature.scramble.controller.ListingController;
import com.revature.scramble.controller.SessionController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import static io.javalin.apibuilder.ApiBuilder.*;

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

        app.routes(() -> {
            path("", ()->{
                get(HomeController.homepageEntered);
            });
            path("session", ()->{
                get(SessionController.get_session);
            });
            path("account", ()->{
                post(AccountController.post_account_page);
                get(AccountController.get_logged_in_account_page);
            });
            path("listing", ()->{
                get(AccountController.get_logged_in_account_page);
                path("user-listings", ()->{
                    get(ListingController.get_user_listings_by_id);
                });
                path("all-listings", ()->{
                    get(ListingController.get_all_listings);
                });
                path("manage", ()->{
                    get(AccountController.get_logged_in_account_page);
                    post(EntryController.get_listing_info_page);
                    path("create", ()->{
                        post(EntryController.post_create_update_entry);
                    });
                    path("entry", ()->{
                        path("{list_id}", ()->{
                            get(EntryController.get_all_entries);
                        });
                    });
                });
            });
            path("logout", ()->{
                get(HomeController.logout);
            });
        });
    }
    
}