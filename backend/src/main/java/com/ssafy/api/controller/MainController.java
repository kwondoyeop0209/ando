package com.ssafy.api.controller;


import com.ssafy.api.response.gu.guGetRes;
import com.ssafy.api.service.gu.GuService;
import com.ssafy.db.dto.GetGuResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/main")
@Api(value = "ando")
@RequiredArgsConstructor
public class MainController {

    final GuService guService;

    @ApiOperation(value = "test", notes = "test", response = List.class)
    @GetMapping("/shorts")
    public ResponseEntity<String> topShorts() {
        try {
            return new ResponseEntity<String>("성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/sigungu")
    public ResponseEntity<guGetRes> getGu(){
        List<GetGuResDto> getGuResDtoList;
        try {
            getGuResDtoList = guService.getGuList();
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(guGetRes.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),null
            ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(guGetRes.of(200,"Success", getGuResDtoList));
    }


}
