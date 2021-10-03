package com.ssafy.api.response.space;

import com.ssafy.db.dto.space.CountAndCoordDto;
import com.ssafy.db.entity.Dong;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountByDongGetRes {

    List<CountAndCoordDto> list;

    public List<CountAndCoordDto> ofCCTV(List<Dong> dongList){
        list = new ArrayList<CountAndCoordDto>();
        for(Dong d : dongList){
            this.list.add(new CountAndCoordDto(d.getDong(), d.getCctvCnt(),d.getLat(),d.getLng()));
        }
        return list;
    }

    public List<CountAndCoordDto> ofBar(List<Dong> dongList){
        list = new ArrayList<CountAndCoordDto>();
        for(Dong d : dongList){
            this.list.add(new CountAndCoordDto(d.getDong(), d.getBarCnt(),d.getLat(),d.getLng()));
        }
        return list;
    }

    public List<CountAndCoordDto> ofPolice(List<Dong> dongList){
        list = new ArrayList<CountAndCoordDto>();
        for(Dong d : dongList){
            this.list.add(new CountAndCoordDto(d.getDong(), d.getPoliceCnt(),d.getLat(),d.getLng()));
        }
        return list;
    }

    public List<CountAndCoordDto> ofGuard(List<Dong> dongList){
        list = new ArrayList<CountAndCoordDto>();
        for(Dong d : dongList){
            this.list.add(new CountAndCoordDto(d.getDong(), d.getGuardHouseCnt(),d.getLat(),d.getLng()));
        }
        return list;
    }

    public List<CountAndCoordDto> ofLight(List<Dong> dongList){
        list = new ArrayList<CountAndCoordDto>();
        for(Dong d : dongList){
            this.list.add(new CountAndCoordDto(d.getDong(), d.getLightCnt(),d.getLat(),d.getLng()));
        }
        return list;
    }
}
