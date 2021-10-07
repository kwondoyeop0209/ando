package com.ssafy.db.mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface DongNameMapping {
    default String getGuName(){
        return getSiGunGuGu();
    }
    String getDong();
    @JsonIgnore
    String getSiGunGuGu();
}
