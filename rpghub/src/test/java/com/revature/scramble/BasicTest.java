package com.revature.scramble;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicTest {
    
    @Test
    public void testBasicTest(){
        Assert.assertEquals(1 + 1, 2);
    }
}