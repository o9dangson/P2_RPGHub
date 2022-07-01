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
                get(SessionController.fetch_get_session);
            });
            path("account", ()->{
                //Account Page
                post(AccountController.post_account_page);
                //ReRouting
                get(AccountController.get_logged_in_account_page);
            });
            path("listing", ()->{
                //ReRouting
                get(AccountController.get_logged_in_account_page);
                //Listing Page
                post(ListingController.post_listing_info_page);
                path("user-listings", ()->{
                    get(ListingController.fetch_get_user_listings_by_id);
                });
                path("all-listings", ()->{
                    get(ListingController.fetch_get_all_listings);
                });
                path("manage", ()->{
                    //ReRouting
                    get(AccountController.get_logged_in_account_page);
                    path("{list_id}", ()->{
                        get(ListingController.fetch_get_particular_listing);
                        path("entry", ()->{
                            get(EntryController.fetch_get_all_entries);
                            path("create", ()->{
                                post(EntryController.fetch_post_create_entry);
                            });
                            path("update", ()->{
                                post(EntryController.fetch_post_update_entry);
                            });
                            path("delete", ()->{
                                post(EntryController.fetch_post_delete_entry);
                            });
                        });
                    });
                });
                path("create", ()->{
                    post(ListingController.fetch_post_create_listing);
                });
                path("update", ()->{
                    post(ListingController.fetch_post_update_listing);
                });
                path("delete", ()->{
                    post(ListingController.fetch_post_delete_listing);
                });
            });
            path("logout", ()->{
                get(HomeController.logout);
            });
        });
    }
    
}