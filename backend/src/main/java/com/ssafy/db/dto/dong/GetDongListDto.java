package com.ssafy.db.dto.dong;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetDongListDto implements Comparable<GetDongListDto>{

    Long id;
    String dong;


    @Override
    public int compareTo(GetDongListDto o) {
        return this.dong.compareTo(o.dong);
    }

}
