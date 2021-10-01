package com.ssafy.api.controller;


import com.ssafy.api.response.dong.DongListGetRes;
import com.ssafy.api.response.gu.guGetRes;
import com.ssafy.api.service.dong.DongService;
import com.ssafy.api.service.gu.GuService;
import com.ssafy.db.dto.dong.GetDongListDto;
import com.ssafy.db.dto.gu.GetGuDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/main")
@Api(value = "ando")
@RequiredArgsConstructor
public class MainController {

    final GuService guService;
    final DongService dongService;

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
    public ResponseEntity<guGetRes> getGuList(){
        List<GetGuDto> getGuDtoList;
        try {
            getGuDtoList = guService.getGuList();
            return ResponseEntity.status(HttpStatus.OK).body(guGetRes.of(200,"Success", getGuDtoList));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(guGetRes.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),null));
        }
    }

    @GetMapping("/dong/{sigungu}")
    public ResponseEntity<DongListGetRes> getDongList(@PathVariable Long sigungu) {
        List<GetDongListDto> getDongListDtoList;
        try {
            if(sigungu<1 || sigungu>26)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DongListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Gu id is not correct",null));
            getDongListDtoList = dongService.getDongList(sigungu);
            return ResponseEntity.status(HttpStatus.OK).body(DongListGetRes.of(HttpStatus.OK.value(), "Success",getDongListDtoList));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DongListGetRes.of(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage() ,null));
        }
    }


}
