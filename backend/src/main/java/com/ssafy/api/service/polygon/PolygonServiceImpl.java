package com.ssafy.api.service.polygon;

import com.ssafy.db.mapping.PolygonMapping;
import com.ssafy.db.repository.PolygonRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
public class PolygonServiceImpl implements PolygonService{
    final PolygonRepository polygonRepository;

    @Override
    public List<String> getPolygon(Long id) throws NotFoundException {
        PolygonMapping polygon = polygonRepository.findByDong_Id(id).orElseThrow(()->new NotFoundException(id+"Dong polygon Not Found!"));
        StringTokenizer st = new StringTokenizer(polygon.getCoordinates(),"-");
        List<String> polygonList = new ArrayList<>();
        while (st.hasMoreTokens()){
            polygonList.add(st.nextToken());
        }
        if(polygonList.isEmpty())
            throw new NotFoundException(id+" Dong Polygon is empty!");
        return polygonList;
    }
}
