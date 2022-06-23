package com.revature.scramble.repository;


import com.revature.scramble.repository.entities.UserInfo;
import com.revature.scramble.repository.interfaces.UserInfoDaoInterface;
import com.revature.scramble.util.ConnectionFactory;


public class UserInfoDao implements UserInfoDaoInterface{

    @Override
    public UserInfo select_user_by_info_id(int info_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserInfo select_by_user_id(int user_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserInfo select_user_by_char_name(String char_name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean select_user_by_is_mod(boolean is_mod) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update_user_by_frozen_status(boolean is_frozen) {
        // TODO Auto-generated method stub
        return false;
    }

    
}
