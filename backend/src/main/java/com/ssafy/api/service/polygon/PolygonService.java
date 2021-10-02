package com.ssafy.api.service.polygon;

import com.ssafy.db.mapping.PolygonMapping;
import javassist.NotFoundException;

public interface PolygonService {
    PolygonMapping getPolygon(Long id) throws NotFoundException;
}
