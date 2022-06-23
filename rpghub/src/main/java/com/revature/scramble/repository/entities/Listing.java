package com.revature.scramble.repository.entities;

public class Listing{

    private int list_Id;
    private int user_id;
    private String list_name;
    private String dungeonName;
    private int max_size;
    private int cur_size;
    
    public Listing(int listingId, int groupLeaderId, String list_name, String dungeonName, int max_size, int cur_size){
        this.list_Id = listingId;
        this.user_id = groupLeaderId;
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

    public int getGroupLeaderId() {
        return user_id;
    }

    public void setGroupLeaderId(int groupLeaderId) {
        this.user_id = groupLeaderId;
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
    
}