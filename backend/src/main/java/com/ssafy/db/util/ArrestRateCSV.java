package com.ssafy.db.util;

import com.opencsv.CSVReader;
import com.ssafy.db.entity.ArrestRate;
import com.ssafy.db.entity.CrimeType;
import com.ssafy.db.entity.Gu;
import com.ssafy.db.repository.arrestRateRepository;
import com.ssafy.db.repository.crimeTypeRepository;
import com.ssafy.db.repository.guRepository;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArrestRateCSV {

    @Autowired
    guRepository guRepository;

    @Autowired
    crimeTypeRepository crimeTypeRepository;

    @Autowired
    arrestRateRepository arrestRateRepository;

    public void inputArrestRate() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("arrest_2018_utf8.csv"), "UTF-8"));
        String [] nextLine;
        String [] type ={"살인","살인","강도","강도","강간","강간","절도","절도","폭력","폭력"};

        reader.readNext();
        reader.readNext();
        reader.readNext();

        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[1]);

            int cnt = 0;

            for (int i = 4; i < nextLine.length; i++) {

                ArrestRate arrest_rate = new ArrestRate();
                if(i%2 == 0)
                    arrest_rate.setType("발생");
                else
                    arrest_rate.setType("검거");

                Optional<CrimeType> crimeOptional = crimeTypeRepository.findByType(type[cnt++]);
                if (crimeOptional.isPresent()) {
                    CrimeType crime_type = crimeOptional.get();
                    arrest_rate.setCrimeType(crime_type);
                }
                arrest_rate.setCount(Integer.parseInt(nextLine[i].trim()));
                arrest_rate.setYear(2018);
                Optional<Gu> guOptional = guRepository.findByGu(nextLine[1]);
                if (guOptional.isPresent()) {
                    Gu gu = guOptional.get();
                    arrest_rate.setSiGunGu(gu);
                    arrestRateRepository.save(arrest_rate);
                }
            }
        }
    }

}
