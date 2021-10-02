package com.ssafy.api.controller;


import com.ssafy.api.response.space.Space5RankingGetRes;
import com.ssafy.api.response.space.SpaceCorrelationGetRes;
import com.ssafy.api.response.dong.CountByDongGetRes;
import com.ssafy.api.response.space.SpaceCountGetRes;
import com.ssafy.api.service.bar.BarService;
import com.ssafy.api.service.cctv.CCTVService;
import com.ssafy.api.service.dong.DongService;
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
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/space/cctv")
@Api(value = "ando")
public class CCTVController {

    @Autowired
    SpaceService spaceService;
    @Autowired
    CCTVService cctvService;

    @ApiOperation(value = "cctv개수 목록", notes = "동별 CCTV 개수 목록", response = List.class)
    @GetMapping("")
    public ResponseEntity<CountByDongGetRes> getCCTVCountList() {
        try {
            CountByDongGetRes list = spaceService.getSpaceCount("cctv");
            return new ResponseEntity<CountByDongGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "cctv 상세정보 목록", notes = "동별 CCTV 상세정보를 목록으로 반환", response = List.class)
    @GetMapping("/{dong}")
    public ResponseEntity<List<CctvInfoMapping>> getCCTVInfo(@PathVariable Long dong) {
        try {
            List<CctvInfoMapping> list = cctvService.getCCTVInfoByDongId(dong);
            return new ResponseEntity<List<CctvInfoMapping>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "cctv 상관관계 정보", notes = "구별 CCTV 개수 목록, 구별 범죄 범거수 목록을 객체로 반환", response = List.class)
    @GetMapping("/graph")
    public ResponseEntity<SpaceCorrelationGetRes> getCCTVCorrelation() {
        try {
            SpaceCorrelationGetRes list = cctvService.getCCTVCorrelation();
            return new ResponseEntity<SpaceCorrelationGetRes>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "cctv 동별, 구별 개수 정보", notes = "해당 동,구 cctv 개수 반환", response = List.class)
    @GetMapping("/count/{id}")
    public ResponseEntity<SpaceCountGetRes> getCCTVCount(@PathVariable Long id) {
        try {
            SpaceCountGetRes spaceCountGetRes = cctvService.getCCTVCount(id);
            return new ResponseEntity<SpaceCountGetRes>(spaceCountGetRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "cctv 주변 순위 5개", notes = "해당 동 cctv 개수 순위 주변 5개 개수정보", response = List.class)
    @GetMapping("/ranking/{id}")
    public ResponseEntity<Space5RankingGetRes> getCCTV5Ranking(@PathVariable Long id) {
        try {
            Space5RankingGetRes spaceCountGetRes = cctvService.getCCTVCount5List(id);
            return new ResponseEntity<Space5RankingGetRes>(spaceCountGetRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
