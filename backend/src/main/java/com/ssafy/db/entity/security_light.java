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
public class security_light extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "dong_id")
    com.ssafy.db.entity.dong dong;

    String address;
    double lat;
    double lng;
}
