package com.ssafy.api.service.crimetime;

import com.ssafy.db.mapping.CrimeTimeMapping;
import com.ssafy.db.repository.CrimeTimeRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrimeTimeServiceImpl implements CrimeTimeService{

    final CrimeTimeRepository crimeTimeRepository;

    @Override
    public List<CrimeTimeMapping> getCrimeTimeList(Long crimeId, int year) throws NotFoundException {

        List<CrimeTimeMapping> list = crimeTimeRepository.findAllByCrimeType_IdAndYear(crimeId,year);

        if(list.isEmpty())
            throw new NotFoundException("CrimeTimeList is empty!");

        return list;
    }
}
