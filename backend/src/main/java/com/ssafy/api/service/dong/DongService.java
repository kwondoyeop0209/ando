package com.ssafy.api.service.dong;

import com.ssafy.db.dto.dong.GetDongListDto;
import javassist.NotFoundException;

import java.util.List;

public interface DongService {
    List<GetDongListDto> getDongList(Long id) throws NotFoundException;
}
