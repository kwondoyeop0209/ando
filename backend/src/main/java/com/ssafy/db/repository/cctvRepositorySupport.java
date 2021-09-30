package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.response.cctv.CctvCorrelationGetRes;
import com.ssafy.api.response.dong.CountByDongGetRes;
import com.ssafy.db.dto.CountByDong;
import com.ssafy.db.entity.QArrestRate;
import com.ssafy.db.entity.QCctv;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class cctvRepositorySupport{

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QArrestRate qArrestRate = QArrestRate.arrestRate;
    QCctv qCctv = QCctv.cctv;


    public CctvCorrelationGetRes getCCTVCorrelation(){
//        List<> =
        return null;
    }

}
