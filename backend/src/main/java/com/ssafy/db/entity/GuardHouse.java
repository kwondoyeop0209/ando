package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GuardHouse extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "dongId")
    Dong dong;

    String address;
    String storeName;
    String phoneNumber;
    double lat;
    double lng;
    String police;
}
