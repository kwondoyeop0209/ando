package com.ssafy.api.service.crimeday;

import com.ssafy.db.mapping.CrimeDayMapping;
import com.ssafy.db.repository.CrimeDayRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrimeDayServiceImpl implements CrimeDayService{

    final CrimeDayRepository crimeDayRepository;

    @Override
    public List<CrimeDayMapping> getCrimeDayList(Long crimeId, int year) throws NotFoundException {
        List<CrimeDayMapping> list = crimeDayRepository.findAllByCrimeType_IdAndYear(crimeId,year);
        if(list.isEmpty())
            throw new NotFoundException("CrimeDayList is empty!");
        return list;
    }
}
