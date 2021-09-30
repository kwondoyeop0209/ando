package com.ssafy.api.service.gu;

import com.ssafy.db.dto.GetGuResDto;
import com.ssafy.db.entity.gu;
import com.ssafy.db.repository.guRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class guServiceImpl implements guService{

    final guRepository guRepository;


    @Override
    public List<GetGuResDto> getGuList() throws NotFoundException {
        List<GetGuResDto> getGuResDtoList = new ArrayList<>();
        List<gu> guList = guRepository.findAll();
        if(guList.isEmpty())
            throw new NotFoundException("gu is empty");
        for(gu gu: guList)
            getGuResDtoList.add(new GetGuResDto(gu.getId(),gu.getGu()));
        return getGuResDtoList;
    }

}
