package com.ssafy.api.controller;


import com.ssafy.api.response.space.Space5RankingGetRes;
import com.ssafy.api.response.space.SpaceCorrelationGetRes;
import com.ssafy.api.response.space.CountByDongGetRes;
import com.ssafy.api.response.space.SpaceCountGetRes;
import com.ssafy.api.service.bar.BarService;
import com.ssafy.api.service.cctv.CCTVService;
import com.ssafy.api.service.guardhouse.GuardHouseService;
import com.ssafy.api.service.police.PoliceService;
import com.ssafy.api.service.securitylight.SecurityLightService;
import com.ssafy.api.service.space.SpaceService;
import com.ssafy.db.mapping.BarInfoMapping;
import com.ssafy.db.mapping.CctvInfoMapping;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    BarService barService;
    @Autowired
    PoliceService policeService;
    @Autowired
    GuardHouseService guardHouseService;
    @Autowired
    SecurityLightService securityLightService;

    @ApiOperation(value = "space type(cctv, bar, police, light, guard) 개수 목록", notes = "동별 space type(cctv, bar, police, light, guard)  개수 목록", response = List.class)
    @GetMapping("")
    public ResponseEntity<CountByDongGetRes> getSpaceCountList(@RequestParam String type) {
        try {
            CountByDongGetRes list = spaceService.getSpaceCount(type);
            return new ResponseEntity<CountByDongGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "space type(cctv, bar, police, light, guard) 상세정보 목록", notes = " space type(cctv, bar, police, light, guard) 상세정보를 목록으로 반환", response = List.class)
    @GetMapping("/detail")
    public ResponseEntity<?> getSpaceInfo(@RequestParam String type, @RequestParam Long id) {
        try {
            if(type.equals("cctv")) {
                List<CctvInfoMapping> list = cctvService.getCCTVInfoByDongId(id);
                return new ResponseEntity<List<CctvInfoMapping>>(list, HttpStatus.OK);
            }else if(type.equals("police")){
                List<PoliceInfoMapping> list = policeService.getPoliceInfoByDongId(id);
                return new ResponseEntity<List<PoliceInfoMapping>>(list, HttpStatus.OK);
            }else if(type.equals("bar")){
                List<BarInfoMapping> list = barService.getBarInfoByDongId(id);
                return new ResponseEntity<List<BarInfoMapping>>(list, HttpStatus.OK);
            }else if(type.equals("light")){
                List<SecurityLightInfoMapping> list = securityLightService.getSecurityLightInfoByDongId(id);
                return new ResponseEntity<List<SecurityLightInfoMapping>>(list, HttpStatus.OK);
            }else if(type.equals("guard")){
                List<GuardHouseInfoMapping> list = guardHouseService.getGuardHouseInfoByDongId(id);
                return new ResponseEntity<List<GuardHouseInfoMapping>>(list, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "space 상관관계 정보", notes = "구별 space type(cctv, bar, police, light, guard) 개수 목록, 구별 범죄 범거수 목록/ 발생수 목록을 객체로 반환", response = List.class)
    @GetMapping("/graph")
    public ResponseEntity<SpaceCorrelationGetRes> getSpaceCorrelation(@RequestParam String type) {
        try {
            SpaceCorrelationGetRes list = spaceService.getSpaceCorrelation(type);
            return new ResponseEntity<SpaceCorrelationGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "space type 동별, 구별 개수 정보", notes = "해당 동,구 space type 개수 반환", response = List.class)
    @GetMapping("/count")
    public ResponseEntity<SpaceCountGetRes> getSpaceCount(@RequestParam String type, @RequestParam Long id) {
        try {
            SpaceCountGetRes spaceCountGetRes = spaceService.getSpaceCount(type, id);
            return new ResponseEntity<SpaceCountGetRes>(spaceCountGetRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "space 주변 순위 5개", notes = "해당 동 space 개수 순위 주변 5개 개수정보", response = List.class)
    @GetMapping("/ranking")
    public ResponseEntity<Space5RankingGetRes> getSpace5Ranking(@RequestParam String type, @RequestParam Long id) {
        try {
            Space5RankingGetRes spaceCountGetRes = spaceService.getSpaceCount5List(type,id);
            return new ResponseEntity<Space5RankingGetRes>(spaceCountGetRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
