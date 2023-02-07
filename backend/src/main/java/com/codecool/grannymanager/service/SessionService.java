package com.codecool.grannymanager.service;



import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public class SessionService {
    private Map<String, Long> session = new HashMap<>();

    public void put(String key, Long id){
        session.put(key, id);
    }

    public Long get(String key){
        return session.get(key);
    }

    public void logout(){
        session.clear();
    }


}
