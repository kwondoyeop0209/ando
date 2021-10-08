package com.ssafy.api.service.space;

import com.ssafy.api.response.space.CountByDongGetRes;
import com.ssafy.api.response.space.Space5RankingGetRes;
import com.ssafy.api.response.space.SpaceCorrelationGetRes;
import com.ssafy.api.response.space.SpaceCountGetRes;
import com.ssafy.db.dto.space.CountByDong;
import com.ssafy.db.entity.Dong;
import com.ssafy.db.repository.arrestRateRepository;
import com.ssafy.db.repository.dongRepository;
import com.ssafy.db.repository.dongRepositorySupport;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    dongRepository dongRepository;
    @Autowired
    arrestRateRepository arrestRateRepository;
    @Autowired
    dongRepositorySupport dongRepositorySupport;

    @Override
    public CountByDongGetRes getSpaceCount(String type) {
        List<Dong> dongList = dongRepository.findAll();
        CountByDongGetRes countCountGetRes = new CountByDongGetRes();
        if (type.equals("cctv"))
            countCountGetRes.ofCCTV(dongList);
        else if (type.equals("police"))
            countCountGetRes.ofPolice(dongList);
        else if (type.equals("guard"))
            countCountGetRes.ofGuard(dongList);
        else if (type.equals("light"))
            countCountGetRes.ofLight(dongList);
        else if (type.equals("bar"))
            countCountGetRes.ofBar(dongList);
        return countCountGetRes;
    }

    @Override
    public SpaceCorrelationGetRes getSpaceCorrelation(String type) throws NotFoundException {
        SpaceCorrelationGetRes res = new SpaceCorrelationGetRes();
        if (type.equals("cctv")) {
            res.setCountList(dongRepository.findCCTVGroupBySiGunGu_Id());
            res.setArrestList(arrestRateRepository.findArrestCntByGu());
        } else if (type.equals("police")) {
            res.setCountList(dongRepository.findPoliceGroupBySiGunGu_Id());
            res.setArrestList(arrestRateRepository.findArrestCntByGu());
        } else if (type.equals("guard")){
            res.setCountList(dongRepository.findGuardGroupBySiGunGu_Id());
            res.setArrestList(arrestRateRepository.findGenerationCntByGu());
        }else if(type.equals("light")){
              res.setCountList(dongRepository.findLightGroupBySiGunGu_Id());
              res.setArrestList(arrestRateRepository.findGenerationCntByGu());
        }else if(type.equals("bar")){
            res.setCountList(dongRepository.findBarGroupBySiGunGu_Id());
            res.setArrestList(arrestRateRepository.findGenerationCntByGu());
        }else throw new NotFoundException("type is wrong");

        
        return res;
    }

    @Override
    public SpaceCountGetRes getSpaceCount(String type, Long id) throws NotFoundException {
        SpaceCountGetRes spaceCountGetRes = new SpaceCountGetRes();
        Optional<Dong> dong = dongRepository.findById(id);
        if(dong.isPresent()) {
            if(type.equals("cctv"))
                spaceCountGetRes.setDongCnt(dong.get().getCctvCnt());
            else if(type.equals("police"))
                spaceCountGetRes.setDongCnt(dong.get().getPoliceCnt());
            else if(type.equals("guard"))
                spaceCountGetRes.setDongCnt(dong.get().getGuardHouseCnt());
            else if(type.equals("light"))
                spaceCountGetRes.setDongCnt(dong.get().getLightCnt());
            else if(type.equals("bar"))
                spaceCountGetRes.setDongCnt(dong.get().getBarCnt());
            else throw new NotFoundException("type is wrong");

            spaceCountGetRes.setGuCnt(dongRepositorySupport.getSpaceCountByGuId(type,dong.get().getSiGunGu().getId()));
        }else{
            throw new NotFoundException("Dong id Empty");
        }
        return spaceCountGetRes;
    }

    @Override
    public Space5RankingGetRes getSpaceCount5List(String type, Long id) throws NotFoundException {
        Optional<Dong> dong = dongRepository.findById(id);
        Space5RankingGetRes res = new Space5RankingGetRes();
        Long ranking = dongRepositorySupport.getSpaceRankingInGu(type,id);
        Long cnt = dongRepositorySupport.getDongCountInGu(id);
        Long offset = ranking - 3;
        if(ranking < 3)
            offset = Long.parseLong(1+"");
        if(ranking > cnt-2) offset = cnt-5;

        List<CountByDong> list = dongRepositorySupport.get5List(type, offset,id);

        boolean flag = false;
        for(CountByDong cbd : list){
            if(cbd.getDongname().equals(dong.get().getDong())) flag = true;
        }
        if(!flag){
            CountByDong cbd = new CountByDong();
            cbd.setDongname(dong.get().getDong());
            if(type.equals("cctv"))
                cbd.setCount(dong.get().getCctvCnt());
            else if(type.equals("police"))
                cbd.setCount(dong.get().getPoliceCnt());
            else if(type.equals("guard"))
                cbd.setCount(dong.get().getGuardHouseCnt());
            else if(type.equals("light"))
                cbd.setCount(dong.get().getLightCnt());
            else if(type.equals("bar"))
                cbd.setCount(dong.get().getBarCnt());
            else throw new NotFoundException("type is wrong");
            list.add(2,cbd);
            list.remove(5);
        }
        Collections.sort(list);
        res.setList(list);
        res.setRanking(ranking);
        return res;
    }
}
