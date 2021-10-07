package com.ssafy.api.service.polygon;

import com.ssafy.db.mapping.PolygonMapping;
import javassist.NotFoundException;

import java.util.List;

public interface PolygonService {
    List<String> getPolygon(Long id) throws NotFoundException;
}
