package com.ssafy.api.service.cctv;

import com.ssafy.api.response.cctv.CCTVCountGetRes;
import com.ssafy.db.dto.CCTVCount;
import java.util.List;

public interface CCTVService {

    public CCTVCountGetRes getCCTVCount();
}
