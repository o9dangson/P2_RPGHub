package com.revature.scramble.service;

import java.util.List;

import com.google.gson.Gson;
import com.revature.scramble.repository.EntryDao;
import com.revature.scramble.repository.entities.Entry;

public class EntryService {
    
    private static EntryDao entryDao = new EntryDao();

    public static List<Entry> get_all_entries(int list_id){
        List<Entry> entries = entryDao.select_entry_by_list_id(list_id);
        return entries;
    }

    public static String get_json_string_from_entries(List<Entry> my_list){
        String json_string = "";
        int my_list_size = my_list.size();
        int counter = 0;
        json_string+="{\"my_list\":[";
        for(Entry entry:my_list){
            json_string+=new Gson().toJson(entry);
            counter++;
            if(counter != my_list_size)
                json_string+=",";
        }
        json_string+="]}";
        //System.out.println(json_string);
        return json_string;
    }

    public static int create_new_entry(Entry entry){
        return entryDao.insert_entry(entry);
    }

    public static void update_entry(int entry_id, String status){
        entryDao.update_entry_by_entry_id(entry_id,status);
    }

    public static void delete_entry(int entry_id){
        entryDao.remove_entry_by_entry_id(entry_id);
    }

    public static Entry get_entry_by_entry_id(int entry_id){
        return entryDao.select_entry_by_entry_id(entry_id);
    }
}
