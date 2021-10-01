package com.ssafy.api.service.arrestrate;

import com.ssafy.db.dto.arrestrate.GetGuCrimeListDto;
import com.ssafy.db.dto.arrestrate.GetTotalCrimeListDto;
import com.ssafy.db.entity.ArrestRate;
import com.ssafy.db.repository.ArrestRateRepositorySupport;
import com.ssafy.db.repository.arrestRateRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArrestRateServiceImpl implements ArrestRateService{

    final arrestRateRepository arrestRateRepository;
    final ArrestRateRepositorySupport arrestRateRepositorySupport;

    @Override
    public List<GetTotalCrimeListDto> getTotalCrimeList() throws NotFoundException {
        List<GetTotalCrimeListDto> getTotalCrimeListDtoList = arrestRateRepositorySupport.findSeoulCrime();
        if(getTotalCrimeListDtoList.isEmpty())
            throw new NotFoundException("TotalList is empty");
        return getTotalCrimeListDtoList;
    }

    @Override
    public List<GetGuCrimeListDto> getGuCrimeList(Long id,int year) throws NotFoundException {
        List<GetGuCrimeListDto> getGuCrimeListDtoList = arrestRateRepositorySupport.findGuCrime(id,year);
        if(getGuCrimeListDtoList.isEmpty())
            throw new NotFoundException("GuList is empty");
        return getGuCrimeListDtoList;
    }
}
