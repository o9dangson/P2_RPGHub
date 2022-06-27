package com.revature.scramble;

import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.scramble.repository.LoginInfoDao;
import com.revature.scramble.repository.entities.LoginInfo;

public class ServiceTest {
    
    @Mock
    private LoginInfoDao mockLoginInfoDao;

    @BeforeClass
    public void setup(){
        MockitoAnnotations.openMocks(this);

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

}
