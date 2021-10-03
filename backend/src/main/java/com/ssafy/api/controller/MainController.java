package com.ssafy.api.controller;


import com.ssafy.api.response.dong.DongListGetRes;
import com.ssafy.api.response.gu.guGetRes;
import com.ssafy.api.response.polygon.PolygonGetRes;
import com.ssafy.api.service.dong.DongService;
import com.ssafy.api.service.gu.GuService;
import com.ssafy.api.service.polygon.PolygonService;
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
    final PolygonService polygonService;

    @ApiOperation(value = "test", notes = "test", response = List.class)
    @GetMapping("/shorts")
    public ResponseEntity<String> topShorts() {
        try {
            return new ResponseEntity<String>("성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "구 목록", notes = "구 id, 구 이름 리스트 리턴", response = guGetRes.class)
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

    @ApiOperation(value = "동 목록", notes = "동 id, 동 이름 리스트 리턴", response = DongListGetRes.class)
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
    @ApiOperation(value = "동 폴리곤", notes = "해당 동의 폴리곤 좌표값 리턴", response = DongListGetRes.class)
    @GetMapping("/polygon/{dong}")
    public ResponseEntity<PolygonGetRes> getPolygon(@PathVariable Long dong){
        try {
            if(dong<=0 || dong >= 427)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PolygonGetRes.of(HttpStatus.BAD_REQUEST.value(), "dong is not correct!",null));
            return ResponseEntity.status(HttpStatus.OK).body(PolygonGetRes.of(HttpStatus.OK.value(), "Success", polygonService.getPolygon(dong)));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(PolygonGetRes.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),null));
        }
    }


}
