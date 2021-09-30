package com.ssafy.api.service.gu;

import com.ssafy.db.dto.gu.GetGuDto;
import javassist.NotFoundException;

import java.util.List;

public interface GuService {

    List<GetGuDto> getGuList() throws NotFoundException;
}
