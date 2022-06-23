package com.revature.scramble.repository.entities;

public class Entry{
    private int entry_id;
	private int list_id;
    private int user_id;
	private String user_role;
    private String user_note;
    private String status;


    public Entry(int entry_id, int list_id, int user_id, String user_role, String user_note, String status) {
        this.entry_id = entry_id;
        this.list_id = list_id;
        this.user_id = user_id;
        this.user_role = user_role;
        this.user_note = user_note;
        this.status = status;
    }
    
    public int getEntry_id() {
        return entry_id;
    }
    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }
    public int getList_id() {
        return list_id;
    }
    public void setList_id(int list_id) {
        this.list_id = list_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getUser_role() {
        return user_role;
    }
    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
    public String getUser_note() {
        return user_note;
    }
    public void setUser_note(String user_note) {
        this.user_note = user_note;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}