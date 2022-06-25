package com.revature.scramble;

import java.sql.Connection;
import java.util.List;
import java.util.function.LongBinaryOperator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.scramble.repository.EntryDao;
import com.revature.scramble.repository.ListingDao;
import com.revature.scramble.repository.LoginInfoDao;
import com.revature.scramble.repository.UserInfoDao;
import com.revature.scramble.repository.entities.Entry;
import com.revature.scramble.repository.entities.Listing;
import com.revature.scramble.repository.entities.LoginInfo;
import com.revature.scramble.repository.entities.UserInfo;
import com.revature.scramble.util.ConnectionFactory;

public class RepositoryTest {

    private EntryDao e_dao;
    private ListingDao list_dao;
    private LoginInfoDao login_dao;
    private UserInfoDao user_dao;

    @BeforeClass
    public void setup(){
        //Any setup if needed here.
        e_dao = new EntryDao();
        list_dao = new ListingDao();
        login_dao = new LoginInfoDao();
        user_dao = new UserInfoDao();
    }

    // TC_10, TC_11
    @Test
    public void testEntryDAO(){
        //Insert new listing for this test
        Listing listing = new Listing(-1, 1, "ex_listing", "ex_dungeon", 4, 1);
        int new_list_id = list_dao.insert_listing(listing);
        listing.setListingId(new_list_id);
        //Test Insert Entry
        Entry new_entry = new Entry(-1, new_list_id, 1, "tank", "I'm really good", "Pending");
        int new_entry_id = e_dao.insert_entry(new_entry);
        Assert.assertNotEquals(new_entry_id, 0);
        //Test select_entry_by_entry_id
        Entry select_entry = e_dao.select_entry_by_entry_id(new_entry_id);
        Assert.assertEquals(select_entry.getEntry_id(), new_entry_id);
        //Test select_entry_by_list_id
        List<Entry> new_list_of_entry = e_dao.select_entry_by_list_id(new_list_id);
        Assert.assertEquals(new_list_of_entry.size(), 1);
        //Test remove_entry
        boolean attempt_delete = e_dao.remove_entry_by_entry_id(new_entry_id);
        Assert.assertTrue(attempt_delete);

        //Remove new listing
        list_dao.delete_listing(listing);
    }

    // TC_05, TC_09
    @Test
    public void testListingDao(){
        //Test insert listing
        Listing listing = new Listing(-1, 1, "ex_listing", "ex_dungeon", 4, 1);
        int new_list_id = list_dao.insert_listing(listing);
        listing.setListingId(new_list_id);
        Assert.assertNotEquals(new_list_id, 0);
        //Test select_Listing
        List<Listing> new_list_of_listings = list_dao.select_all_listing();
        boolean is_new_list_found = false;
        for(Listing new_l : new_list_of_listings){
            if(new_l.getListingId() == new_list_id)
                is_new_list_found = true;
        }
        Assert.assertTrue(is_new_list_found);
        //Test select list by user_id
        List<Listing> new_list_of_listings2 = list_dao.select_listing_by_user_id(1);
        Assert.assertTrue( new_list_of_listings2.size() > 0 );
        //Test delete listing
        list_dao.delete_listing(listing);
        is_new_list_found = false;
        new_list_of_listings = list_dao.select_listing_by_user_id(1);
        for(Listing new_l : new_list_of_listings){
            if(new_l.getListingId() == new_list_id)
                is_new_list_found = true;
        }
        Assert.assertFalse(is_new_list_found);
    }

    // TC_01
    @Test
    public void testLoginInfoDao(){
        //Test selectUser by Id
        LoginInfo login_obj = login_dao.selectUser(1);
        Assert.assertNotEquals(login_obj.getUsername(), "failed");
        login_obj = login_dao.selectUser(-1);
        Assert.assertEquals(login_obj.getUsername(), "failed");
        //Test selectUser by username, password
        login_obj = login_dao.selectUser("user1", "pass");
        Assert.assertEquals(login_obj.getUsername(), "user1");
        login_obj = login_dao.selectUser("user0", "pass");
        Assert.assertEquals(login_obj.getUsername(), "failed");
    }

    // TC_01
    @Test
    public void testUserInfoDao(){
        //select_user_by_info_id
        UserInfo user_info_obj = user_dao.select_user_by_info_id(1);
        Assert.assertEquals(user_info_obj.getChar_name(), "AbsoluteTank");
        UserInfo invalid_user_info_obj = user_dao.select_user_by_info_id(-1);
        Assert.assertEquals(invalid_user_info_obj.getInfo_id(), -1);
        //select_user_by_user_id
        user_info_obj = user_dao.select_user_by_user_id(2);
        Assert.assertEquals(user_info_obj.getChar_name(), "TrueHealer");
        invalid_user_info_obj = user_dao.select_user_by_info_id(-1);
        Assert.assertEquals(invalid_user_info_obj.getInfo_id(), -1);
        //select_user_by_char_name
        user_info_obj = user_dao.select_user_by_char_name("TrueHealer");
        Assert.assertEquals(user_info_obj.getChar_name(), "TrueHealer");
        invalid_user_info_obj = user_dao.select_user_by_char_name("-1");
        Assert.assertEquals(invalid_user_info_obj.getChar_name(), "failed");
        //update_user_by_user_id
        boolean attempt_update = user_dao.update_user_by_user_id(1, true);
        Assert.assertTrue(attempt_update);
        attempt_update = user_dao.update_user_by_user_id(-1, true);
        attempt_update = user_dao.update_user_by_user_id(1, false);
    }
}
