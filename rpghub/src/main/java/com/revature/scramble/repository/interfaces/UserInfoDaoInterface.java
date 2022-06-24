package com.revature.scramble.repository.interfaces;

import com.revature.scramble.repository.entities.UserInfo;

public interface UserInfoDaoInterface{

    UserInfo select_user_by_info_id(int info_id);
    UserInfo select_user_by_user_id(int user_id);
    UserInfo select_user_by_char_name(String char_name);
    boolean update_user_by_user_id(int user_id, boolean is_frozen);
    
}