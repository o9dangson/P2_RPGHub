package com.revature.scramble.models;

public class Session {
    public static int user_id = -1;
    public static boolean is_mod = false;
    public static boolean is_frozen = false;
    public static boolean error = false;
    public static String get_json_string(){
        String json_string = String.format("{\"user_id\":%d,\"is_mod\":%b,\"is_frozen\":%b,\"error\":%b}", user_id, is_mod, is_frozen, error);
        return json_string;
    }
}
