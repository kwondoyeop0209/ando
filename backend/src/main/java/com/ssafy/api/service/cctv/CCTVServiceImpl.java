package com.ssafy.api.service.cctv;

import com.ssafy.api.response.space.Space5RankingGetRes;
import com.ssafy.api.response.space.SpaceCorrelationGetRes;
import com.ssafy.api.response.space.SpaceCountGetRes;
import com.ssafy.db.dto.space.CountByDong;
import com.ssafy.db.entity.Dong;
import com.ssafy.db.mapping.CctvInfoMapping;
import com.ssafy.db.repository.arrestRateRepository;
import com.ssafy.db.repository.cctvRepository;
import com.ssafy.db.repository.dongRepository;
import com.ssafy.db.repository.dongRepositorySupport;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CCTVServiceImpl implements CCTVService {

    @Autowired
    cctvRepository cctvRepository;
    @Autowired
    dongRepository dongRepository;
    @Autowired
    dongRepositorySupport dongRepositorySupport;
    @Autowired
    arrestRateRepository arrestRateRepository;

    @Override
    public List<CctvInfoMapping> getCCTVInfoByDongId(Long dongId) {
        return cctvRepository.findAllByDongId(dongId);
    }

    @Override
    public SpaceCorrelationGetRes getCCTVCorrelation() {
        SpaceCorrelationGetRes res = new SpaceCorrelationGetRes();
        res.setCountList(dongRepository.findCCTVGroupBySiGunGu_Id());
        res.setArrestList(arrestRateRepository.findArrestCntByGu());
        return res;
    }

    @Override
    public SpaceCountGetRes getCCTVCount(Long id) throws NotFoundException {
        SpaceCountGetRes spaceCountGetRes = new SpaceCountGetRes();
        Optional<Dong> dong = dongRepository.findById(id);
        if(dong.isPresent()) {
            spaceCountGetRes.setDongCnt(dong.get().getCctvCnt());
            spaceCountGetRes.setGuCnt(dongRepositorySupport.getSpaceCountByGuId("cctv",dong.get().getSiGunGu().getId()));
        }else{
            throw new NotFoundException("Dong id Empty");
        }
        return spaceCountGetRes;
    }

    @Override
    public Space5RankingGetRes getCCTVCount5List(Long id) {
        Space5RankingGetRes res = new Space5RankingGetRes();
        Long ranking = dongRepositorySupport.getSpaceRankingInGu("cctv",id);
        Long cnt = dongRepositorySupport.getDongCountInGu(id);
        Long offset = ranking - 3;
        if(ranking < 3)
            offset = Long.parseLong(1+"");
        if(ranking > cnt-2) offset = cnt-5;

        List<CountByDong> list = dongRepositorySupport.get5List(offset,id);
        res.setList(list);
        res.setRanking(ranking);
        return res;
    }
}
