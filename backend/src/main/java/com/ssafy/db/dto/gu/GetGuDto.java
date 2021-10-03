package com.ssafy.db.dto.gu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetGuDto implements Comparable<GetGuDto>{

    Long id;
    String gu;

    @Override
    public int compareTo(GetGuDto o) {
        return this.gu.compareTo(o.gu);
    }
}
