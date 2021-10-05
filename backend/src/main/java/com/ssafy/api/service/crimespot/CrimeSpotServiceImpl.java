package com.ssafy.api.service.crimespot;

import com.ssafy.db.mapping.CrimeSpotMapping;
import com.ssafy.db.repository.CrimeSpotRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrimeSpotServiceImpl implements CrimeSpotService{

    final CrimeSpotRepository crimeSpotRepository;

    @Override
    public List<CrimeSpotMapping> getCrimeSpotList(Long crimeId, int year) throws NotFoundException {
        List<CrimeSpotMapping> list = crimeSpotRepository.findTop7ByCrimeType_IdAndYearOrderByCountDesc(crimeId,year);
        if(list.isEmpty())
            throw new NotFoundException("CrimeSpotList is empty!");
        return list;
    }
}
