package com.ssafy.api.controller;

import com.ssafy.api.request.arrstrate.GetCrimListReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.arrestrate.GuCrimeListRes;
import com.ssafy.api.response.arrestrate.TotalCrimeListGetRes;
import com.ssafy.api.service.arrestrate.ArrestRateService;
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

    @GetMapping
    public ResponseEntity<?> getCrimeList(GetCrimListReq getCrimListReq){

        try {
            if(getCrimListReq.getGu_id() == null)
                return ResponseEntity.status(HttpStatus.OK).body(TotalCrimeListGetRes.of(HttpStatus.OK.value(), "Success", arrestRateService.getTotalCrimeList()));
            else if(getCrimListReq.getGu_id() <= 0 && getCrimListReq.getGu_id() >= 26){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TotalCrimeListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Gu_id is not correct!",null));
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(GuCrimeListRes.of(HttpStatus.OK.value(), "Success",arrestRateService.getGuCrimeList(getCrimListReq.getGu_id())));
            }
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }

}
