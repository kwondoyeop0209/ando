package com.ssafy.api.controller;

import com.ssafy.api.request.arrstrate.GetCrimeListReq;
import com.ssafy.api.request.arrstrate.GetRateReq;
import com.ssafy.api.request.crimeday.CrimeDayListGetReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.arrestrate.GetRateRes;
import com.ssafy.api.response.arrestrate.GuCrimeListRes;
import com.ssafy.api.response.arrestrate.TopArrestCrimeGuListRes;
import com.ssafy.api.response.arrestrate.TotalCrimeListGetRes;
import com.ssafy.api.response.crimeday.CrimeDayListGetRes;
import com.ssafy.api.response.riskindex.RiskIndexListGetRes;
import com.ssafy.api.service.arrestrate.ArrestRateService;
import com.ssafy.api.service.crimeday.CrimeDayService;
import com.ssafy.api.service.riskindex.RiskIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/crime")
@Api(value = "ando")
@RequiredArgsConstructor
public class CrimeController {

    final ArrestRateService arrestRateService;

    final RiskIndexService riskIndexService;

    final CrimeDayService crimeDayService;

    @ApiOperation(value = "구별 범죄 건수", notes = "null 이면 서울시 3년 발생 건수, gu랑 year 넣으면 해당 구의 해당 년도 유형별 범죄 건수 리턴", response = List.class)
    @GetMapping
    public ResponseEntity<?> getCrimeList(GetCrimeListReq getCrimeListReq){

        try {
            if(getCrimeListReq.getGu() == null && getCrimeListReq.getYear() == 0)
                return ResponseEntity.status(HttpStatus.OK).body(TotalCrimeListGetRes.of(HttpStatus.OK.value(), "Success", arrestRateService.getTotalCrimeList()));
            else if(getCrimeListReq.getGu() == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TotalCrimeListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Gu_id is not correct!",null));
            else if(getCrimeListReq.getGu() <= 0 || getCrimeListReq.getGu() >= 26){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TotalCrimeListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Gu_id is not correct!",null));
            }else if(getCrimeListReq.getYear() <= 2017 || getCrimeListReq.getYear() >= 2021)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TotalCrimeListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Year is not correct!",null));
            else{
                return ResponseEntity.status(HttpStatus.OK).body(GuCrimeListRes.of(HttpStatus.OK.value(), "Success",arrestRateService.getGuCrimeList(getCrimeListReq.getGu(), getCrimeListReq.getYear())));
            }
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }

    @ApiOperation(value = "범죄 예측 분석", notes = "해당 구의 범죄 예측 분석 리스트 리턴", response = RiskIndexListGetRes.class)
    @GetMapping("/forecast/{sigungu}")
    public ResponseEntity<RiskIndexListGetRes> getRiskIndexList(@PathVariable Long sigungu){
        try {
            if(sigungu <= 0 || sigungu >=26 )
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RiskIndexListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Gu_id is not correct!",null));
            else
                return ResponseEntity.status(HttpStatus.OK).body(RiskIndexListGetRes.of(HttpStatus.OK.value(), "Success", riskIndexService.getRiskIndexList(sigungu)));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RiskIndexListGetRes.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),null));
        }
    }

    @ApiOperation(value = "구 발생&검거", notes = "서울시 전체와 해당 구의 해당 년도 발생 건수, 검거 건수 리턴 ", response = GetRateRes.class)
    @GetMapping("/rate")
    public ResponseEntity<GetRateRes> getArrestRate(GetRateReq getRateReq){
        if(getRateReq.getGu() == null || getRateReq.getYear() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GetRateRes.of(HttpStatus.BAD_REQUEST.value(), "Request is not correct!",null));
        else if(getRateReq.getGu() <= 0 || getRateReq.getGu() >= 26)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GetRateRes.of(HttpStatus.BAD_REQUEST.value(), "Gu_id is not correct!",null));
        else if(getRateReq.getYear() <= 2017 || getRateReq.getYear() >= 2021)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GetRateRes.of(HttpStatus.BAD_REQUEST.value(), "Year is not correct!",null));
        else
            return ResponseEntity.status(HttpStatus.OK).body(GetRateRes.of(HttpStatus.OK.value(), "Success", arrestRateService.getRate(getRateReq.getGu(), getRateReq.getYear())));
    }

    @ApiOperation(value = "Top 5 범죄건수&검거건수 목록", notes = "2020년 Top 5 발생 건수와 해당 구, 검거 건수와 해당 구 리턴", response = TopArrestCrimeGuListRes.class)
    @GetMapping("/top")
    public ResponseEntity<TopArrestCrimeGuListRes> getTopArrestCrimeGuList(){
        try {
            return ResponseEntity.ok(TopArrestCrimeGuListRes.of(HttpStatus.OK.value(), "Success", arrestRateService.getTopGuList("검거"), arrestRateService.getTopGuList("발생") ));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(TopArrestCrimeGuListRes.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),null,null));
        }
    }

    @ApiOperation(value = "범죄 건수 요일별 목록", notes = "해당 년도의 해당 범죄유형의 요일별 범죄 건수 목록 리턴", response = TopArrestCrimeGuListRes.class)
    @GetMapping("/day")
    public ResponseEntity<CrimeDayListGetRes> getCrimeDayList(CrimeDayListGetReq crimeDayListGetReq){
        try {
            if(crimeDayListGetReq.getType() == null || crimeDayListGetReq.getYear() == 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CrimeDayListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Request is not correct!",null));
            else if(crimeDayListGetReq.getType() == null  ||crimeDayListGetReq.getType()<=0 || crimeDayListGetReq.getType()>=6)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CrimeDayListGetRes.of(HttpStatus.BAD_REQUEST.value(), "CrimeType is not correct!", null));
            else if(crimeDayListGetReq.getYear() <= 2017 || crimeDayListGetReq.getYear() >= 2021)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CrimeDayListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Year is not correct!",null));
            return ResponseEntity.status(HttpStatus.OK).body(CrimeDayListGetRes.of(HttpStatus.OK.value(), "Success", crimeDayService.getCrimeDayList(crimeDayListGetReq.getType(), crimeDayListGetReq.getYear())));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CrimeDayListGetRes.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),null));
        }
    }
}
