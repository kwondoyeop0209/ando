package com.ssafy.api.service.polygon;

import com.ssafy.db.mapping.PolygonMapping;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PolygonServiceImplTest {

    @Autowired
    PolygonService polygonService;


    public void getPolygon() throws NotFoundException {
        PolygonMapping polygonMapping = polygonService.getPolygon(Long.valueOf(1));
        System.out.println(polygonMapping.getCoordinates());
    }
}