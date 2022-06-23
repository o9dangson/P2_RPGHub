package com.revature.scramble.repository.entities;

public class UserInfo {
    private int info_id;
    private int userId;
    private String char_name;
    private Boolean is_mod;
    private Boolean is_frozen;


public UserInfo(int Info_id, int userId, String char_name, Boolean is_mod, Boolean is_frozen){
    this.info_id = Info_id;
    this.userId = userId;
    this.char_name = char_name;
    this.is_mod = is_mod;
    this.is_frozen = is_frozen;
}


public int getInfo_id() {
    return info_id;
}


public void setInfo_id(int info_id) {
    this.info_id = info_id;
}


public int getUserId() {
    return userId;
}


public void setUserId(int userId) {
    this.userId = userId;
}


public String getChar_name() {
    return char_name;
}


public void setChar_name(String char_name) {
    this.char_name = char_name;
}


public Boolean getIs_mod() {
    return is_mod;
}


public void setIs_mod(Boolean is_mod) {
    this.is_mod = is_mod;
}


public Boolean getIs_frozen() {
    return is_frozen;
}


public void setIs_frozen(Boolean is_frozen) {
    this.is_frozen = is_frozen;
}



 }