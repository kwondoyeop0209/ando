package com.ssafy.api.response.dong;

import com.ssafy.db.dto.CountByDong;
import com.ssafy.db.entity.Dong;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountByDongGetRes {

    List<CountByDong> list;

    public List<CountByDong> ofCCTV(List<Dong> dongList){
        list = new ArrayList<CountByDong>();
        for(Dong d : dongList){
            this.list.add(new CountByDong(d.getDong(), d.getCctvCnt()));
        }
        return list;
    }

    public List<CountByDong> ofBar(List<Dong> dongList){
        list = new ArrayList<CountByDong>();
        for(Dong d : dongList){
            this.list.add(new CountByDong(d.getDong(), d.getBarCnt()));
        }
        return list;
    }

    public List<CountByDong> ofPolice(List<Dong> dongList){
        list = new ArrayList<CountByDong>();
        for(Dong d : dongList){
            this.list.add(new CountByDong(d.getDong(), d.getPoliceCnt()));
        }
        return list;
    }

    public List<CountByDong> ofGuard(List<Dong> dongList){
        list = new ArrayList<CountByDong>();
        for(Dong d : dongList){
            this.list.add(new CountByDong(d.getDong(), d.getGuardHouseCnt()));
        }
        return list;
    }

    public List<CountByDong> ofLight(List<Dong> dongList){
        list = new ArrayList<CountByDong>();
        for(Dong d : dongList){
            this.list.add(new CountByDong(d.getDong(), d.getLightCnt()));
        }
        return list;
    }
}
