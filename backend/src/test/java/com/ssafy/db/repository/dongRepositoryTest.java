package com.ssafy.db.repository;

import com.ssafy.db.entity.Dong;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class dongRepositoryTest {

    @Autowired
    dongRepository dongRepository;


    public void getDong() throws IOException, ParseException {
        List<Dong> dongList = dongRepository.findAll();
        String url  = "https://dapi.kakao.com/v2/local/search/address.json?";
        for(Dong dong:dongList){
            String address = dong.getSiGunGu().getGu() + " " + dong.getDong();
            String param = "analyze_type=similar&page=1&size=10&query=" + URLEncoder.encode(address,"UTF-8");
            String totalUrl = url + param;

            URL resultUrl = null;
            HttpURLConnection conn = null;

            String responseData = "";
            BufferedReader br = null;
            StringBuffer sb = null;
            String returnData = "";
            resultUrl = new URL(totalUrl);
            conn = (HttpURLConnection) resultUrl.openConnection();

            // http 요청에 필요한 타입 정의 실시
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK d66d41baf08aba94184ed23802a0d16b");

            // http 요청 실시
            conn.connect();
            System.out.println("http 요청 방식 : " + "GET");
            System.out.println("http 요청 타입 : " + "application/json");
            System.out.println("http 요청 주소 : " + url);
            System.out.println("http 요청 데이터 : " + param);
            System.out.println("");


            // http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();

            while ((responseData = br.readLine()) != null) {
                sb.append(responseData); // StringBuffer에 응답받은 데이터 순차적으로 저장 실시
            }

            // 메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
            returnData = sb.toString();

            // http 요청 응답 코드 확인 실시
            String responseCode = String.valueOf(conn.getResponseCode());
            System.out.println("http 응답 코드 : " + responseCode);
            System.out.println("http 응답 데이터 : " + returnData);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(returnData);
            if(jsonObject.get("documents")!=null){
                JSONArray jsonArray = (JSONArray) jsonObject.get("documents");

                if(jsonArray.size() != 0){
                    JSONObject adr = (JSONObject) jsonArray.get(0);

                    System.out.println(adr.get("x"));
                    System.out.println(adr.get("y"));

                    dong.setLng(Double.parseDouble((String) adr.get("x")));
                    dong.setLat(Double.parseDouble((String) adr.get("y")));

                    dongRepository.save(dong);
                }

            }


        }
    }
}