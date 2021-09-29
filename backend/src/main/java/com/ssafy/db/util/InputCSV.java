package com.ssafy.db.util;

import com.opencsv.CSVReader;
import com.ssafy.db.entity.cctv;
import com.ssafy.db.entity.dong;
import com.ssafy.db.repository.cctvRepository;
import com.ssafy.db.repository.dongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class InputCSV {

    @Autowired
    dongRepository dongRepository;

    @Autowired
    cctvRepository cctvRepository;

    public void inputCCTV() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("cctv.csv"), "UTF-8"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {   // 2
            for (int i = 0; i < nextLine.length; i++) {
                cctv cctv = new cctv();
                cctv.setAddress(nextLine[1]);
                cctv.setLat(Double.parseDouble(nextLine[2]));
                cctv.setLat(Double.parseDouble(nextLine[3]));
                cctv.setNumber(Integer.parseInt(nextLine[4]));
                Optional<dong> dongOptional = dongRepository.findByDongAndSiGunGu_Gu(nextLine[5],nextLine[0]);
                if(dongOptional.isPresent()){
                    dong dong = dongOptional.get();
                    cctv.setDong(dong);
                    cctvRepository.save(cctv);
                }
                System.out.println(i + " " + nextLine[i]);
            }
            System.out.println();
        }
    }

}
