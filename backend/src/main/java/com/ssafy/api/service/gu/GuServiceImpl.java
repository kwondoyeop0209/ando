package com.ssafy.api.service.gu;

import com.ssafy.db.dto.gu.GetGuDto;
import com.ssafy.db.entity.Gu;
import com.ssafy.db.repository.guRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuServiceImpl implements GuService {

    final guRepository guRepository;


    @Override
    public List<GetGuDto> getGuList() throws NotFoundException {
        List<GetGuDto> getGuDtoList = new ArrayList<>();
        List<Gu> guList = guRepository.findAll();
        if(guList.isEmpty())
            throw new NotFoundException("gu is empty");
        for(Gu gu: guList)
            getGuDtoList.add(new GetGuDto(gu.getId(),gu.getGu()));
        return getGuDtoList;
    }

}
