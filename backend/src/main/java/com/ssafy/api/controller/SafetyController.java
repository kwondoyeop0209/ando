package com.ssafy.api.controller;

import com.ssafy.api.response.safety.TopSafetyGetRes;
import com.ssafy.api.service.safety.SafetyService;
import com.ssafy.db.dto.dong.GetTopSafetyListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
