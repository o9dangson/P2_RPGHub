package com.revature.scramble;

import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.scramble.repository.EntryDao;
import com.revature.scramble.repository.ListingDao;
import com.revature.scramble.repository.LoginInfoDao;
import com.revature.scramble.repository.entities.Listing;
import com.revature.scramble.repository.entities.LoginInfo;
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
        when(mockLoginInfoDao.selectUser("user1", "pass")).thenReturn(new LoginInfo(1, "user1", "pass"));
        when(mockLoginInfoDao.selectUser("user0", "pass")).thenReturn(new LoginInfo(-1, "failed", "failed"));

        LoginInfo result = mockLoginInfoDao.selectUser("user1", "pass");
        Assert.assertEquals(result.getUserId(), 1);
        result = mockLoginInfoDao.selectUser("user0", "pass");
        Assert.assertEquals(result.getUserId(), -1);
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

    }

    @Test
    public void entryService(){
        
    }

}
