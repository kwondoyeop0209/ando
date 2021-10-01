package com.ssafy.api.service.riskindex;

import com.ssafy.db.dto.riskindex.RiskIndexDto;
import javassist.NotFoundException;

import java.util.List;

public interface RiskIndexService {
    List<RiskIndexDto> getRiskIndexList(Long id) throws NotFoundException;
}
