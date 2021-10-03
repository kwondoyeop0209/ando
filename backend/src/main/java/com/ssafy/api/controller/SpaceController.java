package com.ssafy.api.controller;


import com.ssafy.api.response.space.SpaceCorrelationGetRes;
import com.ssafy.api.response.space.CountByDongGetRes;
import com.ssafy.api.service.bar.BarService;
import com.ssafy.api.service.guardhouse.GuardHouseService;
import com.ssafy.api.service.police.PoliceService;
import com.ssafy.api.service.securitylight.SecurityLightService;
import com.ssafy.api.service.space.SpaceService;
import com.ssafy.db.mapping.BarInfoMapping;
import com.ssafy.db.mapping.GuardHouseInfoMapping;
import com.ssafy.db.mapping.PoliceInfoMapping;
import com.ssafy.db.mapping.SecurityLightInfoMapping;
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
    BarService barService;
    @Autowired
    PoliceService policeService;
    @Autowired
    GuardHouseService guardHouseService;
    @Autowired
    SecurityLightService securityLightService;

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

    @ApiOperation(value = "bar 상세정보 목록", notes = "동별 bar 상세정보를 목록으로 반환", response = List.class)
    @GetMapping("/bar/{dong}")
    public ResponseEntity<List<BarInfoMapping>> getBarCount(@PathVariable Long dong) {
        try {
            List<BarInfoMapping> list = barService.getBarInfoByDongId(dong);
            return new ResponseEntity<List<BarInfoMapping>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "bar 상관관계 정보", notes = "구별 bar 개수 목록, 구별 범죄 발생수 목록을 객체로 반환", response = List.class)
    @GetMapping("/bar/graph")
    public ResponseEntity<SpaceCorrelationGetRes> getBarCorrelation() {
        try {
            SpaceCorrelationGetRes list = barService.getBarCorrelation();
            return new ResponseEntity<SpaceCorrelationGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "파출소 상세정보 목록", notes = "동별 파출소 상세정보를 목록으로 반환", response = List.class)
    @GetMapping("/police/{dong}")
    public ResponseEntity<List<PoliceInfoMapping>> getPoliceCount(@PathVariable Long dong) {
        try {
            List<PoliceInfoMapping> list = policeService.getPoliceInfoByDongId(dong);
            return new ResponseEntity<List<PoliceInfoMapping>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "police 상관관계 정보", notes = "구별 police 개수 목록, 구별 범죄 범거수 목록을 객체로 반환", response = List.class)
    @GetMapping("/police/graph")
    public ResponseEntity<SpaceCorrelationGetRes> getPoliceCorrelation() {
        try {
            SpaceCorrelationGetRes list = policeService.getPoliceCorrelation();
            return new ResponseEntity<SpaceCorrelationGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "지킴이집 상세정보 목록", notes = "동별 지킴이집 상세정보를 목록으로 반환", response = List.class)
    @GetMapping("/guard/{dong}")
    public ResponseEntity<List<GuardHouseInfoMapping>> getGuardHouseCount(@PathVariable Long dong) {
        try {
            List<GuardHouseInfoMapping> list = guardHouseService.getGuardHouseInfoByDongId(dong);
            return new ResponseEntity<List<GuardHouseInfoMapping>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "지킴이집 상관관계 정보", notes = "구별 지킴이집 개수 목록, 구별 범죄 발생수 목록을 객체로 반환", response = List.class)
    @GetMapping("/guard/graph")
    public ResponseEntity<SpaceCorrelationGetRes> getGuardHouseCorrelation() {
        try {
            SpaceCorrelationGetRes list = guardHouseService.getGuardHouseCorrelation();
            return new ResponseEntity<SpaceCorrelationGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "보안등 상세정보 목록", notes = "동별 보안등 상세정보를 목록으로 반환", response = List.class)
    @GetMapping("/light/{dong}")
    public ResponseEntity<List<SecurityLightInfoMapping>> getSecurityLightCount(@PathVariable Long dong) {
        try {
            List<SecurityLightInfoMapping> list = securityLightService.getSecurityLightInfoByDongId(dong);
            return new ResponseEntity<List<SecurityLightInfoMapping>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "보안등 상관관계 정보", notes = "구별 보안등 개수 목록, 구별 범죄 발생수 목록을 객체로 반환", response = List.class)
    @GetMapping("/light/graph")
    public ResponseEntity<SpaceCorrelationGetRes> getSecurityLightCorrelation() {
        try {
            SpaceCorrelationGetRes list = securityLightService.getSecurityLightCorrelation();
            return new ResponseEntity<SpaceCorrelationGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
