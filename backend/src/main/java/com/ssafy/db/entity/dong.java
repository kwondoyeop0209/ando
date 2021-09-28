package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class dong extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "gu_id")
    gu siGunGu;

    String dong;

    int area;
    double safety_index;
    int guard_house_cnt;
    int police_cnt;
    int cctv_cnt;
    int bar_cnt;
    int light_cnt;

}
