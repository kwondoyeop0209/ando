package com.ssafy.api.service.gu;

import com.ssafy.db.dto.GetGuResDto;
import javassist.NotFoundException;

import java.util.List;

public interface GuService {

    List<GetGuResDto> getGuList() throws NotFoundException;
}
