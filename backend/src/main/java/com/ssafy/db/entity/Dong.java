package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;

@Entity
@Getter
@Setter
public class Dong extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "guId")
    Gu siGunGu;

    String dong;

    double area;
    double safetyIndex;
    int guardHouseCnt;
    int policeCnt;
    int cctvCnt;
    int barCnt;
    int lightCnt;

}