package com.revature.scramble.controller;

import com.revature.scramble.models.Session;

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
}
