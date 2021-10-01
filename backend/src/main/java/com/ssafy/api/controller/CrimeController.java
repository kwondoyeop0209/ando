package com.ssafy.api.controller;

import com.ssafy.api.request.arrstrate.GetCrimeListReq;
import com.ssafy.api.request.arrstrate.GetRateReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.arrestrate.GetRateRes;
import com.ssafy.api.response.arrestrate.GuCrimeListRes;
import com.ssafy.api.response.arrestrate.TopArrestCrimeGuListRes;
import com.ssafy.api.response.arrestrate.TotalCrimeListGetRes;
import com.ssafy.api.response.riskindex.RiskIndexListGetRes;
import com.ssafy.api.service.arrestrate.ArrestRateService;
import com.ssafy.api.service.riskindex.RiskIndexService;
import io.swagger.annotations.Api;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/crime")
@Api(value = "ando")
@RequiredArgsConstructor
public class CrimeController {

    final ArrestRateService arrestRateService;

    final RiskIndexService riskIndexService;

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

    @GetMapping("/top")
    public ResponseEntity<TopArrestCrimeGuListRes> getTopArrestCrimeGuList(){
        try {
            return ResponseEntity.ok(TopArrestCrimeGuListRes.of(HttpStatus.OK.value(), "Success", arrestRateService.getTopGuList("검거"), arrestRateService.getTopGuList("발생") ));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(TopArrestCrimeGuListRes.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),null,null));
        }
    }

}
