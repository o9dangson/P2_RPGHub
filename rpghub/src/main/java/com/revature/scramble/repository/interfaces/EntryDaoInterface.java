package com.revature.scramble.repository.interfaces;

import java.util.List;

import com.revature.scramble.repository.entities.Entry;

public interface EntryDaoInterface {
    int insert_entry(Entry entryEntity);
    Entry select_entry_by_entry_id(int entry_id);
    List<Entry> select_entry_by_list_id(int list_id);
    boolean update_entry_by_entry_id(int entry_id, String varName, String value);
    boolean remove_entry_by_entry_id(int entry_id);
}