package com.ssafy.api.response.cctv;

import com.ssafy.api.response.dong.CountByDongGetRes;
import com.ssafy.db.dto.CountByDong;
import com.ssafy.db.entity.ArrestRate;
import com.ssafy.db.mapping.ArrestMapping;
import com.ssafy.db.mapping.CctvCorrelationMapping;
import com.ssafy.db.mapping.CctvInfoMapping;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CctvCorrelationGetRes {
     List<CctvCorrelationMapping> countList;
     List<ArrestMapping> arrestList;

}
