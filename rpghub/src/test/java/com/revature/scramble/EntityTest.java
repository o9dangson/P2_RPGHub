package com.revature.scramble;

import java.util.List;

import org.json.*;
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

public class EntityTest {
    
    @Test
    public void testEntryEntity(){
        Entry actual = new Entry(3, 3, 3, "tank", null, null);
        Entry expected = new Entry(2, 3, 3, "tank", null, "Accepted");
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(3, 2, 3, null, null, null);
        expected = new Entry(3, 3, 3, "tank", null, "Accepted");
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(4, 3, 4, "DPS", null, "Accepted");
        expected = new Entry(4, 3, 3, null, "hello", "Pending");
        Assert.assertNotEquals(actual, expected);
        
        actual = new Entry(3, 2, 3, "tank", null, null);
        expected = new Entry(3, 2, 3, "tank", null, "Accepted");
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(3, 3, 3, "tank", null, null);
        expected = new Entry(3, 3, 3, "tank", null, "Accepted");
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(3, 3, 3, "tank", null, null);
        expected = new Entry(3, 3, 3, "tank", "null", "Accepted");
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(3, 3, 3, "tank", null, null);
        expected = new Entry(3, 3, 3, "tank", null, "Accepted");
        Assert.assertNotEquals(actual, expected);

        actual = new Entry(3, 2, 3, "tank", null, null);
        expected = actual;
        Assert.assertEquals(actual, expected);
        expected = null;
        actual = new Entry(3, 3, 3, "tank", null, null);
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(3, 2, 3, "tank", "null", null);
        expected = new Entry(3, 2, 3, "tank", "null", "Pending");
        Assert.assertNotEquals(actual, expected);

        actual = new Entry(3, 3, 3, "tank", null, null);
        expected = new Entry(3, 3, 3, "tank", null, "null");
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(3, 3, 2, "tank", null, null);
        expected = new Entry(3, 3, 3, "tank", null, null);
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(3, 3, 3, "tank", null, null);
        expected = new Entry(3, 3, 3, "tank", "null", null);
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(3, 3, 3, null, null, null);
        expected = new Entry(3, 3, 3, "tank", null, "null");
        Assert.assertNotEquals(actual, expected);
        actual = new Entry(3, 3, 3, "healer", null, null);
        expected = new Entry(3, 3, 3, "tank", null, "null");
        Assert.assertNotEquals(actual, expected);

        actual.setEntry_id(1);
        actual.setList_id(1);
        actual.setUser_id(1);
        actual.setUser_role("healer");
        actual.setUser_note("hey");
        actual.setStatus("pending");
        expected.setEntry_id(1);
        expected.setList_id(1);
        expected.setUser_id(1);
        expected.setUser_role("healer");
        expected.setUser_note("hey");
        expected.setStatus("pending");
        Assert.assertEquals(actual, expected);

        String temp = actual.toString();
        Assert.assertTrue(temp instanceof String);

    }

    @Test
    public void testUserInfoEntity(){
        UserInfo actual_info = new UserInfo(3,2,"dungeonMaster", false, false);
        UserInfo new_info = new UserInfo(3,2,"dungeonMaster",false,false);

        Assert.assertEquals(actual_info,new_info);

        new_info = actual_info;
        Assert.assertEquals(actual_info, new_info);

        actual_info.setInfo_id(2);
        new_info.setInfo_id(2);
        Assert.assertEquals(actual_info.getInfo_id(), new_info.getInfo_id());

        actual_info.setUserId(3);
        new_info.setUserId(3);
        Assert.assertEquals(actual_info.getUserId(), new_info.getUserId());

        actual_info = new UserInfo(3,2,"dungeonMaster", false, false);
        new_info = new UserInfo(3,2,"dungeonMaster", false, false);
        actual_info.setChar_name("damageMan");
        new_info.setChar_name("healerMan");
        Assert.assertFalse(actual_info.getChar_name().equals(new_info.getChar_name()));

        actual_info.setIs_mod(true);
        new_info.setIs_mod(true);
        Assert.assertEquals(actual_info.getIs_mod(), new_info.getIs_mod());

        new_info.setIs_frozen(true);
        Assert.assertTrue(new_info.getIs_frozen());

        actual_info = new UserInfo(3,2, null, false, false);
        new_info = new UserInfo(3,2,"dungeonMaster", false, false);
        Assert.assertNotEquals(actual_info, new_info);

        actual_info = new UserInfo(3,2, null, false, false);
        new_info = new UserInfo(2,2,null, false, false);
        Assert.assertNotEquals(actual_info, new_info);

        actual_info = new UserInfo(3,2, null, false, null);
        new_info = new UserInfo(3,2, null, false, false);
        Assert.assertNotEquals(actual_info, new_info);
        
        actual_info = new UserInfo(3,2, null, false, false);
        new_info = new UserInfo(3,2, null, false, true);
        Assert.assertNotEquals(actual_info, new_info);

        actual_info = new UserInfo(3,2, null, null, false);
        new_info = new UserInfo(3,2, null, false, false);
        Assert.assertNotEquals(actual_info, new_info);

        actual_info = new UserInfo(3,2, null, false, false);
        new_info = new UserInfo(3,2, null, true, false);
        Assert.assertNotEquals(actual_info, new_info);

        actual_info = new UserInfo(3,2, null, false, false);
        new_info = new UserInfo(3,1, null, false, false);
        Assert.assertNotEquals(actual_info, new_info);
        

        String temp = actual_info.toString();
        Assert.assertTrue(temp instanceof String);
    }

    @Test
    public void testListingEntity(){
        Listing testListing = new Listing(1, 1, "kappa", "dungeonName", 5, 2);
        Listing mockListing = testListing;
        Assert.assertEquals(testListing, mockListing);
        mockListing = null;
        Assert.assertNotEquals(testListing, mockListing);
        Entry mockEntry = new Entry(1, 2, 2, "user_role", "user_note", "status");
        Assert.assertNotEquals(testListing, mockEntry);
        mockListing = new Listing(1, 1, "kappa", "dungeonName", 5, 3);
        Assert.assertNotEquals(testListing, mockListing);
        mockListing = new Listing(1, 1, "kappa", null, 5, 2);
        Assert.assertNotEquals(testListing, mockListing);
        mockListing = new Listing(1, 1, "kappa", "notit", 5, 2);
        Assert.assertNotEquals(testListing, mockListing);
        mockListing = new Listing(1, 1, "kap", "dungeonName", 5, 2);
        Assert.assertNotEquals(testListing, mockListing);
        mockListing = new Listing(1, 1, null, "dungeonName", 5, 2);
        Assert.assertNotEquals(testListing, mockListing);
        mockListing = new Listing(1, 1, "kappa", "dungeonName", 4, 2);
        Assert.assertNotEquals(testListing, mockListing);
        mockListing = new Listing(1, 2, "kappa", "dungeonName", 5, 2);
        Assert.assertNotEquals(testListing, mockListing);

        Assert.assertTrue(testListing.toString() instanceof String);

        // testListing
        // testListing
        // testListing
        // testListing
        // testListing
        // testListing

    }

    @Test
    public void testLoginInfoEntity(){
        LoginInfo testInfo = new LoginInfo(1, "apple", null);
        LoginInfo mockInfo = new LoginInfo(1, null, null);
        Assert.assertNotEquals(testInfo, mockInfo);
        testInfo = new LoginInfo(1, null, "apple");
        Assert.assertNotEquals(testInfo, mockInfo);
        UserInfo mockInfo2 = new UserInfo(1, 1, "apple", true, false);
        Assert.assertNotEquals(testInfo, mockInfo2);
    }
}
