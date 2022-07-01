package com.revature.scramble.repository.entities;

public class UserInfo {
    private int info_id;
    private int userId;
    private String char_name;
    private Boolean is_mod;
    private Boolean is_frozen;


<<<<<<< HEAD
    public UserInfo(int Info_id, int userId, String char_name, Boolean is_mod, Boolean is_frozen){
        this.info_id = Info_id;
=======
    public UserInfo(int info_id, int userId, String char_name, Boolean is_mod, Boolean is_frozen){
        this.info_id = info_id;
>>>>>>> listingpage_backup
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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((char_name == null) ? 0 : char_name.hashCode());
        result = prime * result + info_id;
        result = prime * result + ((is_frozen == null) ? 0 : is_frozen.hashCode());
        result = prime * result + ((is_mod == null) ? 0 : is_mod.hashCode());
        result = prime * result + userId;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserInfo other = (UserInfo) obj;
        if (char_name == null) {
            if (other.char_name != null)
                return false;
        } else if (!char_name.equals(other.char_name))
            return false;
        if (info_id != other.info_id)
            return false;
        if (is_frozen == null) {
            if (other.is_frozen != null)
                return false;
        } else if (!is_frozen.equals(other.is_frozen))
            return false;
        if (is_mod == null) {
            if (other.is_mod != null)
                return false;
        } else if (!is_mod.equals(other.is_mod))
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "UserInfo [char_name=" + char_name + ", info_id=" + info_id + ", is_frozen=" + is_frozen + ", is_mod="
                + is_mod + ", userId=" + userId + "]";
    }
}