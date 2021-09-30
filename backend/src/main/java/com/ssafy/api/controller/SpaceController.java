package com.ssafy.api.controller;


import com.ssafy.api.response.cctv.CctvCorrelationGetRes;
import com.ssafy.api.response.dong.CountByDongGetRes;
import com.ssafy.api.service.cctv.CCTVService;
import com.ssafy.api.service.space.SpaceService;
import com.ssafy.db.mapping.CctvInfoMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/space")
@Api(value = "ando")
public class SpaceController {

    @Autowired
    SpaceService spaceService;
    @Autowired
    CCTVService cctvService;
//    @Autowired
//    BarService barService;
//    @Autowired
//    PoliceService policeService;
//    @Autowired
//    GuardHouseService guardHouseService;
//    @Autowired
//    SecurityLightService securityLightService;

    @ApiOperation(value = "cctv개수 목록", notes = "동별 CCTV 개수 목록", response = List.class)
    @GetMapping("/cctv")
    public ResponseEntity<CountByDongGetRes> getCCTVCount() {
        try {
            CountByDongGetRes list = spaceService.getSpaceCount("cctv");
            return new ResponseEntity<CountByDongGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @ApiOperation(value = "bar개수 목록", notes = "동별 bar 개수 목록", response = List.class)
    @GetMapping("/bar")
    public ResponseEntity<CountByDongGetRes> getBarCount() {
        try {
            CountByDongGetRes list = spaceService.getSpaceCount("bar");
            return new ResponseEntity<CountByDongGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @ApiOperation(value = "파출소개수 목록", notes = "동별 파출소 개수 목록", response = List.class)
    @GetMapping("/police")
    public ResponseEntity<CountByDongGetRes> getPoliceCount() {
        try {
            CountByDongGetRes list = spaceService.getSpaceCount("police");
            return new ResponseEntity<CountByDongGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @ApiOperation(value = "지킴이집개수 목록", notes = "동별 지킴이집 개수 목록", response = List.class)
    @GetMapping("/guard")
    public ResponseEntity<CountByDongGetRes> getGuardHouseCount() {
        try {
            CountByDongGetRes list = spaceService.getSpaceCount("guard");
            return new ResponseEntity<CountByDongGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @ApiOperation(value = "보안등개수 목록", notes = "동별 보안등 개수 목록", response = List.class)
    @GetMapping("/light")
    public ResponseEntity<CountByDongGetRes> getSecurityLightCount() {
        try {
            CountByDongGetRes list = spaceService.getSpaceCount("light");
            return new ResponseEntity<CountByDongGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "cctv 상세정보 목록", notes = "동별 CCTV 상세정보를 목록으로 반환", response = List.class)
    @GetMapping("/cctv/{dong}")
    public ResponseEntity<List<CctvInfoMapping>> getCCTVCount(@PathVariable Long dong) {
        try {
            List<CctvInfoMapping> list = cctvService.getCCTVInfoByDongId(dong);
            return new ResponseEntity<List<CctvInfoMapping>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "cctv 상관관계 정보", notes = "구별 CCTV 개수 목록, 구별 범죄 범거수 목록을 객체로 반환", response = List.class)
    @GetMapping("/cctv/graph")
    public ResponseEntity<CctvCorrelationGetRes> getCCTVCorrelation() {
        try {
            CctvCorrelationGetRes list = cctvService.getCCTVCorrelation();
            return new ResponseEntity<CctvCorrelationGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
