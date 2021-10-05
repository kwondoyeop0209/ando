package com.ssafy.api.service.dong;

import com.ssafy.api.response.dong.SafetyRankingGetRes;
import com.ssafy.api.response.dong.SpaceRankingGetRes;
import com.ssafy.db.dto.dong.GetDongListDto;
import com.ssafy.db.entity.Dong;
import com.ssafy.db.mapping.DongNameMapping;
import com.ssafy.db.mapping.DongRanking;
import com.ssafy.db.mapping.DongSafetyIndex;
import com.ssafy.db.mapping.LatLngMapping;
import com.ssafy.db.repository.dongRepository;
import com.ssafy.db.repository.dongRepositorySupport;
import java.util.Optional;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DongServiceImpl implements DongService{
    final dongRepository dongRepository;

    @Autowired
    dongRepositorySupport dongRepositorySupport;

    @Override
    public DongNameMapping getGuNameByDong(Long id) throws NotFoundException {
        Optional<DongNameMapping> dong = dongRepository.findById(id,DongNameMapping.class);
        if(dong.isPresent()){
            return dong.get();
        }else{
            throw new NotFoundException("Dong is Empty");
        }
    }

    @Override
    public List<GetDongListDto> getDongList(Long id) throws NotFoundException {
        List<Dong> dongList = dongRepository.findBySiGunGu_Id(id);
        List<GetDongListDto> getDongListDtoList = new ArrayList<>();
        if(dongList.isEmpty())
            throw new NotFoundException("Dong is Empty");
        for (Dong dong : dongList)
            getDongListDtoList.add(new GetDongListDto(dong.getId(),dong.getDong()));
        Collections.sort(getDongListDtoList);
        return getDongListDtoList;
    }

    @Override
    public SafetyRankingGetRes getSafetyRanking(Long id) throws NotFoundException {
        SafetyRankingGetRes safetyRankingGetRes = new SafetyRankingGetRes();

        Optional<Dong> dongSafetyIndex = dongRepository.findById(id);

        if(dongSafetyIndex.isPresent()) {
            safetyRankingGetRes.setSafetyIndex(dongSafetyIndex.get().getSafetyIndex());
            safetyRankingGetRes.setRankingOfSeoul(dongRepositorySupport.getRanking(id));
            safetyRankingGetRes.setRankingOfGu(dongRepositorySupport.getGuRanking(id));
        }else{
           throw new NotFoundException("Dong id Empty");
        }
        return safetyRankingGetRes;
    }

    @Override
    public SpaceRankingGetRes getSpaceRanking(Long id) throws NotFoundException {
        SpaceRankingGetRes spaceRankingGetRes = new SpaceRankingGetRes();

        Optional<Dong> dong = dongRepository.findById(id);

        if(dong.isPresent()) {
            spaceRankingGetRes.setCctvRanking(dongRepositorySupport.getSpaceRanking("cctv",id));
            spaceRankingGetRes.setPoliceRanking(dongRepositorySupport.getSpaceRanking("police",id));
            spaceRankingGetRes.setBarRanking(dongRepositorySupport.getSpaceRanking("bar",id));
            spaceRankingGetRes.setLightRanking(dongRepositorySupport.getSpaceRanking("light",id));
            spaceRankingGetRes.setGuardRanking(dongRepositorySupport.getSpaceRanking("guard",id));

            spaceRankingGetRes.setCctvAvg(dongRepositorySupport.getSpaceAvg("cctv"));
            spaceRankingGetRes.setPoliceAvg(dongRepositorySupport.getSpaceAvg("police"));
            spaceRankingGetRes.setBarAvg(dongRepositorySupport.getSpaceAvg("bar"));
            spaceRankingGetRes.setLightAvg(dongRepositorySupport.getSpaceAvg("light"));
            spaceRankingGetRes.setGuardAvg(dongRepositorySupport.getSpaceAvg("guard"));

            spaceRankingGetRes.setCctv(dong.get().getCctvCnt());
            spaceRankingGetRes.setBar(dong.get().getBarCnt());
            spaceRankingGetRes.setPolice(dong.get().getPoliceCnt());
            spaceRankingGetRes.setLight(dong.get().getLightCnt());
            spaceRankingGetRes.setGuard(dong.get().getGuardHouseCnt());
        }else{
            throw new NotFoundException("Dong id Empty");
        }
        return spaceRankingGetRes;
    }

    @Override
    public LatLngMapping getLatLng(Long id) throws NotFoundException {
        LatLngMapping mapping =  dongRepository.findById(id,LatLngMapping.class).orElseThrow(()->new NotFoundException(id+"Dong Not Found!"));
        return mapping;
    }

}
