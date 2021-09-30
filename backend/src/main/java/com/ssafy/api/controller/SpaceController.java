package com.ssafy.api.controller;


import com.ssafy.api.response.cctv.CCTVCountGetRes;
import com.ssafy.api.service.cctv.CCTVService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/space")
@Api(value = "ando")
public class SpaceController {

    @Autowired
    CCTVService cctvService;

    @ApiOperation(value = "cctv개수 목록", notes = "동별 CCTV 개수 목록", response = List.class)
    @GetMapping("/cctv")
    public ResponseEntity<CCTVCountGetRes> getCCTVCount() {
        try {
            CCTVCountGetRes list = cctvService.getCCTVCount();
            return new ResponseEntity<CCTVCountGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
