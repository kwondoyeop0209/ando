package com.ssafy.api.service.polygon;

import com.ssafy.db.mapping.PolygonMapping;
import com.ssafy.db.repository.PolygonRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolygonServiceImpl implements PolygonService{
    final PolygonRepository polygonRepository;

    @Override
    public PolygonMapping getPolygon(Long id) throws NotFoundException {
        PolygonMapping polygon = polygonRepository.findByDong_Id(id).orElseThrow(()->new NotFoundException(id+"Dong polygon Not Found!"));
        return polygon;
    }
}
