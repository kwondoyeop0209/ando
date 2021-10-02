package com.ssafy.api.response.polygon;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.mapping.PolygonMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolygonGetRes extends BaseResponseBody {
    PolygonMapping polygon;

    public static PolygonGetRes of(Integer statusCode, String message,PolygonMapping polygon){
        PolygonGetRes polygonGetRes = new PolygonGetRes();
        polygonGetRes.setStatusCode(statusCode);
        polygonGetRes.setMessage(message);
        polygonGetRes.setPolygon(polygon);
        return polygonGetRes;
    }
}
