package com.ssafy.db.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountByDong {

    String dongname;
    int count;

    public CountByDong(){

    }
    public CountByDong(String dong, int count){
    this.dongname = dong;
    this.count = count;
    }
}
