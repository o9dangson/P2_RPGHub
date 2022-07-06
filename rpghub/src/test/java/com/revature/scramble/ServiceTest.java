package com.revature.scramble;

import java.util.List;

import org.json.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.scramble.repository.EntryDao;
import com.revature.scramble.repository.ListingDao;
import com.revature.scramble.repository.LoginInfoDao;
import com.revature.scramble.repository.entities.Entry;
import com.revature.scramble.repository.entities.Listing;
import com.revature.scramble.repository.entities.LoginInfo;
import com.revature.scramble.repository.entities.UserInfo;
import com.revature.scramble.service.AccountService;
import com.revature.scramble.service.EntryService;
import com.revature.scramble.service.ListingService;


public class ServiceTest {
    
    @Mock
    private LoginInfoDao mockLoginInfoDao;
    private ListingDao mockListingDao;
    private EntryDao mockEntryDao;
    private Listing mockListing;


    @BeforeClass
    public void setup(){
        MockitoAnnotations.openMocks(this);
        mockListingDao = new ListingDao();
        mockEntryDao = new EntryDao();
    }

    //TC_02
    @Test
    public void testAccountService(){
        LoginInfo login_result = AccountService.verify_details("user1", "pass");
        Assert.assertEquals(login_result.getUserId(), 1);
        Assert.assertEquals(login_result.getPassword(), "pass");

        login_result = AccountService.verify_details("user0", "pass");
        Assert.assertEquals(login_result.getUserId(), -1);

        UserInfo user_result = AccountService.get_user_details(1);
        Assert.assertEquals(user_result.getInfo_id(), 1);
        Assert.assertEquals(user_result.getUserId(), 1);
        Assert.assertEquals(user_result.getChar_name(), "AbsoluteTank");
        Assert.assertEquals(user_result.getChar_name(), "AbsoluteTank");
        Assert.assertFalse(user_result.getIs_mod());
        Assert.assertFalse(user_result.getIs_frozen());

        login_result = AccountService.get_login_info_from_user_id(1);
        LoginInfo mock_login = null; 
        Assert.assertNull(mock_login);
        Assert.assertNotEquals(login_result, mock_login);

        mock_login = new LoginInfo(-1, "default", "default");
        Assert.assertNotEquals(login_result, mock_login);

        mock_login.setUserId(1); mock_login.setUsername("user1"); mock_login.setPassword("pass");
        Assert.assertEquals(login_result, mock_login);
        Assert.assertEquals(login_result.toString(), mock_login.toString());
        
        List<LoginInfo> login_list = AccountService.get_all_login_info();
        Assert.assertTrue(login_list instanceof List);

        AccountService.moderate_user_account(1, true);
        user_result = AccountService.get_user_details(1);
        Assert.assertTrue(user_result.getIs_frozen());
        AccountService.moderate_user_account(1, false);
        
        String json_string = AccountService.get_json_string_from_login_obj(login_result);
        Assert.assertTrue(isJSONValid(json_string));

        json_string = AccountService.get_json_string_from_login_list(login_list);
        Assert.assertTrue(isJSONValid(json_string));
    }

    @Test
    public void testListingService(){
        Listing listing = new Listing(-1, 1, "ex_listing", "ex_dungeon", 4, 1);

        //test create listing
        int new_list_id = ListingService.create_listing(listing);
        listing.setListingId(new_list_id);
        Assert.assertEquals(listing.getUser_id(), mockListingDao.select_listing_by_list_id(new_list_id).getUser_id());

        //Test update listing
        ListingService.update_listing_service(listing);
        Assert.assertEquals(listing.getMax_size(),mockListingDao.select_listing_by_list_id(new_list_id).getMax_size());
        Assert.assertEquals(listing.getCur_size(),mockListingDao.select_listing_by_list_id(new_list_id).getCur_size());

        //test get all listings
        List<Listing> all_listings = ListingService.get_all_listings();
        Assert.assertSame(all_listings,mockListingDao.select_all_listing());

        //test get listings by userid
        Assert.assertSame(ListingService.get_listings_by_user_id(listing.getUser_id()),mockListingDao.select_listing_by_user_id(listing.getUser_id()));
        
        //test add listing
        int mockListing2 = ListingService.create_listing(new Listing(-1, 1, "ex_listing", "ex_dungeon", 4, 1));
        all_listings.add(all_listings.size(),ListingService.get_listing_by_list_id(mockListing2));
        all_listings = ListingService.get_all_listings();
        Assert.assertEquals(all_listings.size(),mockListingDao.select_all_listing().size());
        mockListingDao.insert_listing(mockListingDao.select_listing_by_list_id(mockListing2));
        Assert.assertEquals(ListingService.get_all_listings().size(),mockListingDao.select_all_listing().size());

        //test remove listing
        ListingService.delete_listing_using_obj(mockListing2);
        all_listings = ListingService.get_all_listings();
        Assert.assertEquals(all_listings.size(),mockListingDao.select_all_listing().size());
        Assert.assertSame(ListingService.get_all_listings(), mockListingDao.select_all_listing());

        //test get_json_string_from_list
        new_list_id = ListingService.create_listing(listing);
        all_listings = ListingService.get_all_listings();
        Assert.assertTrue(isJSONValid(ListingService.get_json_string_from_list(all_listings)));
    }

    @Test
    public void entryService(){
        Listing listing = new Listing(-1, 1, "ex_listing", "ex_dungeon", 4, 1);
        //Test get all listing
        Assert.assertSame(EntryService.get_all_entries(listing.getListingId()), mockEntryDao.select_entry_by_list_id(listing.getListingId()));
        //Test create listing
        listing.setListingId(ListingService.create_listing(listing));
        Entry entry = new Entry(-1,listing.getListingId(),listing.getUser_id(),"Tank","tank","Pending");
        int new_entry_id = EntryService.create_new_entry(entry);
        entry.setEntry_id(new_entry_id);
        Assert.assertSame(new_entry_id,entry.getEntry_id());

        //Test entry update and get entry by entry id
        EntryService.update_entry(entry.getEntry_id(), "Rejected");
        entry = EntryService.get_entry_by_entry_id(entry.getEntry_id());
        Assert.assertEquals(entry.getStatus(), "Rejected");

        //Put code here
        new_entry_id = EntryService.create_new_entry(entry);
        List<Entry> all_entries = EntryService.get_all_entries(listing.getListingId());
        Assert.assertTrue(isJSONValid(EntryService.get_json_string_from_entries(all_entries)));
        
        EntryService.delete_entry(new_entry_id);
        int final_size = all_entries.size()-1;
        all_entries = EntryService.get_all_entries(listing.getListingId());
        Assert.assertEquals(all_entries.size(), final_size);
        
    }

    public boolean isJSONValid(String get_json_string_from_entries) {
        try {
            new JSONObject(get_json_string_from_entries);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(get_json_string_from_entries);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
