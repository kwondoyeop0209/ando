package com.ssafy.db.util;

import com.opencsv.CSVReader;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.Optional;

import static com.ssafy.db.entity.Qsecurity_light.security_light;

@Component
public class InputCSV {

    @Autowired
    dongRepository dongRepository;

    @Autowired
    cctvRepository cctvRepository;

    @Autowired
    security_lightRepository security_lightRepository;

    @Autowired
    barRepository barRepository;

    @Autowired
    police_officeRepository police_officeRepository;

    @Autowired
    guard_houseRepository guard_houseRepository;



    public void inputCCTV() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("cctv.csv"), "UTF-8"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {   // 2
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println(i + " " + nextLine[i]);
            }
            cctv cctv = new cctv();
            cctv.setAddress(nextLine[1]);
            cctv.setLat(Double.parseDouble(nextLine[2]));
            cctv.setLng(Double.parseDouble(nextLine[3]));
            cctv.setNumber(Integer.parseInt(nextLine[4]));
            Optional<dong> dongOptional = dongRepository.findByDongAndSiGunGu_Gu(nextLine[5],nextLine[0]);
            if(dongOptional.isPresent()){
                dong dong = dongOptional.get();
                cctv.setDong(dong);
                cctvRepository.save(cctv);
            }
            System.out.println();
        }
    }

    public void inputLight() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("boan.csv"), "UTF-8"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {   // 2
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println(i + " " + nextLine[i]);
            }
            security_light light = new security_light();
            light.setAddress(nextLine[2]);
            light.setLat(Double.parseDouble(nextLine[3]));
            light.setLng(Double.parseDouble(nextLine[4]));
            Optional<dong> dongOptional = dongRepository.findByDongAndSiGunGu_Gu(nextLine[7],nextLine[6]);
            if(dongOptional.isPresent()){
                dong dong = dongOptional.get();
                light.setDong(dong);
                security_lightRepository.save(light);
            }
            System.out.println();
        }
    }

    public void inputBar() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("bar.csv"), "UTF-8"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {   // 2
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println(i + " " + nextLine[i]);
            }
            bar bar = new bar();
            bar.setAddress(nextLine[0]);
            bar.setLng(Double.parseDouble(nextLine[7]));
            bar.setLat(Double.parseDouble(nextLine[8]));
            bar.setName(nextLine[2]);
            Optional<dong> dongOptional = dongRepository.findByDongAndSiGunGu_Gu(nextLine[9],nextLine[5]);
            if(dongOptional.isPresent()){
                dong dong = dongOptional.get();
                bar.setDong(dong);
                barRepository.save(bar);
            }
            System.out.println();
        }
    }

    public void inputKaraoke() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("karaoke.csv"), "UTF-8"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {   // 2
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println(i + " " + nextLine[i]);
            }
            bar bar = new bar();
            bar.setAddress(nextLine[0]);
            bar.setLng(Double.parseDouble(nextLine[3]));
            bar.setLat(Double.parseDouble(nextLine[4]));
            bar.setName(nextLine[2]);
            Optional<dong> dongOptional = dongRepository.findByDongAndSiGunGu_Gu(nextLine[6],nextLine[5]);
            if(dongOptional.isPresent()){
                dong dong = dongOptional.get();
                bar.setDong(dong);
                barRepository.save(bar);
            }
            System.out.println();
        }
    }

    public void inputPolice() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("police.csv"), "UTF-8"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {   // 2
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println(i + " " + nextLine[i]);
            }
            police_office police_office = new police_office();
            police_office.setAddress(nextLine[1]);
            police_office.setLng(Double.parseDouble(nextLine[2]));
            police_office.setLat(Double.parseDouble(nextLine[3]));
            Optional<dong> dongOptional = dongRepository.findByDongAndSiGunGu_Gu(nextLine[4],nextLine[0]);
            if(dongOptional.isPresent()){
                dong dong = dongOptional.get();
                police_office.setDong(dong);
                police_officeRepository.save(police_office);
            }
            System.out.println();
        }
    }

    public void inputHouse() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("house.csv"), "UTF-8"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {   // 2
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println(i + " " + nextLine[i]);
            }
            guard_house guard_house = new guard_house();
            guard_house.setAddress(nextLine[7]);
            guard_house.setLat(Double.parseDouble(nextLine[2]));
            guard_house.setLng(Double.parseDouble(nextLine[3]));
            guard_house.setStore_name(nextLine[0]);
            guard_house.setPolice(nextLine[5]);
            guard_house.setPhone_number(nextLine[4]);

            Optional<dong> dongOptional = dongRepository.findByDongAndSiGunGu_Gu(nextLine[6],nextLine[1]);
            if(dongOptional.isPresent()){
                dong dong = dongOptional.get();
                guard_house.setDong(dong);
                guard_houseRepository.save(guard_house);
            }
            System.out.println();
        }
    }

}
