package com.ssafy.api.response.space;

import com.ssafy.db.dto.space.CountByDong;
import com.ssafy.db.mapping.ArrestMapping;
import com.ssafy.db.mapping.CctvCorrelationMapping;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpaceCorrelationGetRes {
     List<CctvCorrelationMapping> countList;
     List<ArrestMapping> arrestList;
     List<CountByDong> countInGuList;

     int spaceGuCnt;
     int spaceDongCnt;
}
