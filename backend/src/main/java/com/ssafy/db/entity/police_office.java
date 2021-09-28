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
public class police_office extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "dong_id", foreignKey=@ForeignKey(name = "guard_house_fk_dong_id"))
    com.ssafy.db.entity.dong dong;

    String name;
    double lat;
    double lng;
    String address;
    int number;
}
