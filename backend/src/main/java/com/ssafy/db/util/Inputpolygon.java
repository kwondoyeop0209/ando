package com.ssafy.db.util;

import com.opencsv.CSVReader;
import com.ssafy.db.entity.Polygon;
import com.ssafy.db.entity.cctv;
import com.ssafy.db.entity.dong;
import com.ssafy.db.repository.cctvRepository;
import com.ssafy.db.repository.dongRepository;
import com.ssafy.db.repository.polygonRepository;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.StringTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Inputpolygon {

    @Autowired
    dongRepository dongRepository;

    @Autowired
    polygonRepository polygonRepository;

    public void inputPolygon() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("geojson.csv"), "UTF-8"),';');
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
        StringTokenizer st = new StringTokenizer(nextLine[0]);
        String gu = st.nextToken();
        String dongname = st.nextToken();
            Polygon polygon = new Polygon();

            polygon.setCoordinates(nextLine[1]);
            Optional<dong> dongOptional = dongRepository.findByDongAndSiGunGu_Gu(dongname,gu);
            if(dongOptional.isPresent()){
                dong dong = dongOptional.get();
                polygon.setDong(dong);
                polygonRepository.save(polygon);
            }
        }
    }


}