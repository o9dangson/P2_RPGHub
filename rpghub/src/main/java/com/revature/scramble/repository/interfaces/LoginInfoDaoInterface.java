package com.revature.scramble.repository.interfaces;

import java.util.List;

import com.revature.scramble.repository.entities.LoginInfo;

public interface LoginInfoDaoInterface {
    
    LoginInfo selectUser(int id);
    LoginInfo selectUser(String username, String password);
    List<LoginInfo> selectAllUser();
    
}