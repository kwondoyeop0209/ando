package com.ssafy.api.response.polygon;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.mapping.LatLngMapping;
import com.ssafy.db.mapping.PolygonMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolygonGetRes extends BaseResponseBody {
    PolygonMapping polygon;
    LatLngMapping dongLatLng;

    public static PolygonGetRes of(Integer statusCode, String message,PolygonMapping polygon,LatLngMapping dongLatLng){
        PolygonGetRes polygonGetRes = new PolygonGetRes();
        polygonGetRes.setStatusCode(statusCode);
        polygonGetRes.setMessage(message);
        polygonGetRes.setPolygon(polygon);
        polygonGetRes.setDongLatLng(dongLatLng);
        return polygonGetRes;
    }
}
