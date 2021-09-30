package com.ssafy.api.response.cctv;

import com.ssafy.db.dto.CCTVCount;
import com.ssafy.db.entity.Dong;
import java.util.ArrayList;
import java.util.List;

public class CCTVCountGetRes {

    List<CCTVCount> list;

    public List<CCTVCount> of(List<Dong> dongList){
        list = new ArrayList<CCTVCount>();

        for(Dong d : dongList){
            list.add(new CCTVCount(d.getDong(), d.getCctvCnt()));
        }

        return list;
    }
}
