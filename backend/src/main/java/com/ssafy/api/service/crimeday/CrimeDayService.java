package com.ssafy.api.service.crimeday;

import com.ssafy.db.mapping.CrimeDayMapping;
import javassist.NotFoundException;

import java.util.List;

public interface CrimeDayService {
    List<CrimeDayMapping> getCrimeDayList(Long crimeId,int year) throws NotFoundException;
}
