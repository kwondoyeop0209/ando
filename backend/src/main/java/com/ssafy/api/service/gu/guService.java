package com.ssafy.api.service.gu;

import com.ssafy.db.dto.GetGuResDto;
import com.ssafy.db.entity.gu;
import javassist.NotFoundException;

import java.util.List;

public interface guService {

    List<GetGuResDto> getGuList() throws NotFoundException;
}
