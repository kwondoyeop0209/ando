package com.ssafy.api.controller;

import com.ssafy.api.response.dong.SafetyRankingGetRes;
import com.ssafy.api.response.dong.SpaceRankingGetRes;
import com.ssafy.api.response.safety.TopSafetyGetRes;
import com.ssafy.api.service.dong.DongService;
import com.ssafy.api.service.safety.SafetyService;
import com.ssafy.db.dto.dong.GetTopSafetyListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/safety")
@Api(value = "ando")
public class SafetyController {

    @Autowired
    SafetyService safetyService;
    @Autowired
    DongService dongService;

    @ApiOperation(value = "안전지수 상위 10개 목록", notes = "안전지수 상위 10개 목록", response = List.class)
    @GetMapping("/top")
    public ResponseEntity<TopSafetyGetRes> getTopSafety() {
        List<GetTopSafetyListDto> topSafetyGetResList;
        try {
            //상위 10개 목록
            topSafetyGetResList = safetyService.getTopSafetyListDtoList();
            return ResponseEntity.status(HttpStatus.OK).body(TopSafetyGetRes.of(HttpStatus.OK.value(), "Success",topSafetyGetResList));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "동 안전지수 정보", notes = "해당 동의 안전지수, 구 순위, 전체 순위 반환", response = List.class)
    @GetMapping("/point/{id}")
    public ResponseEntity<SafetyRankingGetRes> getSafetyInfo(@PathVariable Long id) {
        SafetyRankingGetRes safetyRankingGetRes;
        try {
            //점수, 구 순위, 시 순위
            safetyRankingGetRes = dongService.getSafetyRanking(id);
            return ResponseEntity.status(HttpStatus.OK).body(safetyRankingGetRes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "동 환경요소 순위 정보", notes = "해당 동의 환경요소 순위와 평균 값 반환", response = List.class)
    @GetMapping("/detail/{id}")
    public ResponseEntity<SpaceRankingGetRes> getSpaceInfo(@PathVariable Long id) {
        SpaceRankingGetRes spaceRankingGetRes;
        try {
            spaceRankingGetRes = dongService.getSpaceRanking(id);
            return ResponseEntity.status(HttpStatus.OK).body(spaceRankingGetRes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
