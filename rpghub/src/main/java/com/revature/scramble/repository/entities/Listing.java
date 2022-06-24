package com.revature.scramble.repository.entities;

public class Listing{

    private int list_Id;
    private int user_id;
    private String list_name;
    private String dungeonName;
    private int max_size;
    private int cur_size;
    
    public Listing(int listingId, int user_id, String list_name, String dungeonName, int max_size, int cur_size){
        this.list_Id = listingId;
        this.user_id = user_id;
        this.list_name = list_name;
        this.dungeonName = dungeonName;
        this.max_size = max_size;
        this.cur_size = cur_size;
    }

    public int getListingId() {
        return list_Id;
    }

    public void setListingId(int listingId) {
        this.list_Id = listingId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getListName() {
        return list_name;
    }

    public void setListName(String list_name) {
        this.list_name = list_name;
    }

    public String getDungeonName() {
        return dungeonName;
    }

    public void setDungeonName(String dungeonName) {
        this.dungeonName = dungeonName;
    }

    public int getMax_size() {
        return max_size;
    }

    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }

    public int getCur_size() {
        return cur_size;
    }

    public void setCur_size(int cur_size) {
        this.cur_size = cur_size;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cur_size;
        result = prime * result + ((dungeonName == null) ? 0 : dungeonName.hashCode());
        result = prime * result + list_Id;
        result = prime * result + ((list_name == null) ? 0 : list_name.hashCode());
        result = prime * result + max_size;
        result = prime * result + user_id;
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
        Listing other = (Listing) obj;
        if (cur_size != other.cur_size)
            return false;
        if (dungeonName == null) {
            if (other.dungeonName != null)
                return false;
        } else if (!dungeonName.equals(other.dungeonName))
            return false;
        if (list_Id != other.list_Id)
            return false;
        if (list_name == null) {
            if (other.list_name != null)
                return false;
        } else if (!list_name.equals(other.list_name))
            return false;
        if (max_size != other.max_size)
            return false;
        if (user_id != other.user_id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Listing [cur_size=" + cur_size + ", dungeonName=" + dungeonName + ", list_Id=" + list_Id
                + ", list_name=" + list_name + ", max_size=" + max_size + ", user_id=" + user_id + "]";
    }
    
}