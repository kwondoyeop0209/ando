package com.ssafy.api.service.crimespot;

import com.ssafy.db.mapping.CrimeSpotMapping;
import javassist.NotFoundException;

import java.util.List;

public interface CrimeSpotService {
    List<CrimeSpotMapping> getCrimeSpotList(Long crimeId,int year) throws NotFoundException;
}
