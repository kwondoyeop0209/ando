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
public class guard_house extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "dong_id")
    com.ssafy.db.entity.dong dong;

    String address;
    String store_name;
    String phone_number;
    double lat;
    double lng;
    String police;
}
