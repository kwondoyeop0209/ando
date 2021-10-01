package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bar extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "dongId")
    Dong dong;

    String name;
    String address;
    double lat;
    double lng;

}