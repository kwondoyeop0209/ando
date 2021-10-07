package com.ssafy.db.dto.space;

import com.ssafy.db.dto.dong.GetDongListDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountByDong implements Comparable<CountByDong>{

    String dongname;
    int count;

    public CountByDong(){

    }
    public CountByDong(String dong, int count){
    this.dongname = dong;
    this.count = count;
    }

    @Override
    public int compareTo(CountByDong o) {
        return o.count -this.count;
    }
}
