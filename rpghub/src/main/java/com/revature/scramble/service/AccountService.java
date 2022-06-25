package com.revature.scramble.service;

import com.revature.scramble.repository.LoginInfoDao;
import com.revature.scramble.repository.UserInfoDao;
import com.revature.scramble.repository.entities.LoginInfo;
import com.revature.scramble.repository.entities.UserInfo;

public class AccountService {
    private static LoginInfoDao login_dao = new LoginInfoDao();
    private static UserInfoDao user_dao = new UserInfoDao();

    public static LoginInfo verify_details(String username, String password){
        return login_dao.selectUser(username, password);
    }

    public static UserInfo get_user_details(int user_id){
        return user_dao.select_user_by_user_id(user_id);
    }
}
