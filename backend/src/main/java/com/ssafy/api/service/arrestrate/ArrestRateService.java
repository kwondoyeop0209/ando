package com.ssafy.api.service.arrestrate;

import com.ssafy.db.dto.arrestrate.GetTotalCrimeListDto;
import javassist.NotFoundException;

import java.util.List;

public interface ArrestRateService {

    List<GetTotalCrimeListDto> getTotalCrimeList() throws NotFoundException;
}
