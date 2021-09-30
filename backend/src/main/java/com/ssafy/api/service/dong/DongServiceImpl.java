package com.ssafy.api.service.dong;

import com.ssafy.db.dto.dong.GetDongListDto;
import com.ssafy.db.entity.Dong;
import com.ssafy.db.repository.dongRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DongServiceImpl implements DongService{
    final dongRepository dongRepository;
    @Override
    public List<GetDongListDto> getDongList(Long id) throws NotFoundException {
        List<Dong> dongList = dongRepository.findBySiGunGu_Id(id);
        List<GetDongListDto> getDongListDtoList = new ArrayList<>();
        if(dongList.isEmpty())
            throw new NotFoundException("Dong is Empty");
        for (Dong dong : dongList)
            getDongListDtoList.add(new GetDongListDto(dong.getId(),dong.getDong()));
        Collections.sort(getDongListDtoList);
        return getDongListDtoList;
    }
}
