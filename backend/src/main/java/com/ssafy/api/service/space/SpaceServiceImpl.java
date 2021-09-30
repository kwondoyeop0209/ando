package com.ssafy.api.service.space;

import com.ssafy.api.response.dong.CountByDongGetRes;
import com.ssafy.db.entity.Dong;
import com.ssafy.db.repository.dongRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    dongRepository dongRepository;

    @Override
    public CountByDongGetRes getSpaceCount(String type) {
        List<Dong> dongList = dongRepository.findAll();
        CountByDongGetRes countCountGetRes = new CountByDongGetRes();
        if(type.equals("cctv"))
            countCountGetRes.ofCCTV(dongList);
        else if(type.equals("police"))
            countCountGetRes.ofPolice(dongList);
        else if(type.equals("guard"))
            countCountGetRes.ofGuard(dongList);
        else if(type.equals("light"))
            countCountGetRes.ofLight(dongList);
        else if(type.equals("bar"))
            countCountGetRes.ofBar(dongList);
        return countCountGetRes;
    }
}
