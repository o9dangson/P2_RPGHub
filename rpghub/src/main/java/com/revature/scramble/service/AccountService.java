package com.revature.scramble.service;

import java.util.List;

import com.google.gson.Gson;
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

    public static LoginInfo get_login_info_from_user_id(int user_id){
        return login_dao.selectUser(user_id);
    }
    
    public static List<LoginInfo> get_all_login_info(){
        return login_dao.selectAllUser();
    }

    public static void moderate_user_account(int user_id, boolean is_frozen){
        user_dao.update_user_by_user_id(user_id, is_frozen);
    }

    public static String get_json_string_from_login_obj(LoginInfo login){
        return new Gson().toJson(login);
    }

    public static String get_json_string_from_login_list(List<LoginInfo> list){
        String json_string = "";
        int my_list_size = list.size();
        int counter = 0;
        json_string+="{\"my_list\":[";
        for(LoginInfo login_obj:list){
            json_string+=new Gson().toJson(login_obj);
            counter++;
            if(counter != my_list_size)
                json_string+=",";
        }
        json_string+="]}";
        return json_string;
    }
}
