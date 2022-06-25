package com.revature.scramble.controller;

import com.revature.scramble.models.Session;
import com.revature.scramble.repository.entities.UserInfo;

import io.javalin.http.Handler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SessionController {

    public static Handler get_session = ctx -> {
        String json_string = Session.get_json_string();
        
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(json_string);
        ctx.json(jsonNode);
    };

    public static void update_session(UserInfo obj){
        Session.user_id = obj.getInfo_id();
        Session.is_mod = obj.getIs_mod();
        Session.is_frozen = obj.getIs_frozen();
        Session.error = false;
    }

    public static void reset_session(){
        Session.user_id = -1;
        Session.is_mod = false;
        Session.is_frozen = false;
        Session.error = false;
    }
}
