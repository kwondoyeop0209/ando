package com.ssafy.api.service.cctv;

import com.ssafy.api.response.cctv.CCTVCountGetRes;
import com.ssafy.db.entity.Dong;
import com.ssafy.db.repository.dongRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CCTVServiceImpl implements CCTVService {

    @Autowired
    dongRepository dongRepository;

    @Override
    public CCTVCountGetRes getCCTVCount() {
//        List<Dong> dongList = dongRepository.findALL();
//        CCTVCountGetRes cctvCountGetRes = new CCTVCountGetRes();
//        cctvCountGetRes.of(dongList);
//        return cctvCountGetRes;
        return null;
    }
}
