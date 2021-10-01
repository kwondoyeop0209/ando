package com.ssafy.api.service.cctv;

import com.ssafy.api.response.cctv.CountByDongGetRes;
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
    public CountByDongGetRes getCCTVCount() {
        List<Dong> dongList = dongRepository.findAll();
        return null;
    }
}
