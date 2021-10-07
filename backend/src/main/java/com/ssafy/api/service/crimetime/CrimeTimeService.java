package com.ssafy.api.service.crimetime;

import com.ssafy.db.mapping.CrimeTimeMapping;
import javassist.NotFoundException;

import java.util.List;

public interface CrimeTimeService {
    List<CrimeTimeMapping> getCrimeTimeList(Long crimeId,int year) throws NotFoundException;
}
