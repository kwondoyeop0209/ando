package com.ssafy.api.response.polygon;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.mapping.LatLngMapping;
import com.ssafy.db.mapping.PolygonMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PolygonGetRes extends BaseResponseBody {
    List<String> polygonList;
    LatLngMapping dongLatLng;

    public static PolygonGetRes of(Integer statusCode, String message,List<String> polygon,LatLngMapping dongLatLng){
        PolygonGetRes polygonGetRes = new PolygonGetRes();
        polygonGetRes.setStatusCode(statusCode);
        polygonGetRes.setMessage(message);
        polygonGetRes.setPolygonList(polygon);
        polygonGetRes.setDongLatLng(dongLatLng);
        return polygonGetRes;
    }
}
