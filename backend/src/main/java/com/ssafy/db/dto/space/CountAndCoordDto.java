package com.ssafy.db.dto.space;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountAndCoordDto {
    String dongname;
    int count;
    double lat;
    double lng;
    Long dongId;

    public CountAndCoordDto(){

    }
    public CountAndCoordDto(String dong, int count, double lat, double lng, Long dongId){
        this.dongname = dong;
        this.count = count;
        this.lat = lat;
        this.lng = lng;
        this.dongId = dongId;
    }

}
