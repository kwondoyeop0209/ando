package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class risk_index extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "gu_id")
    gu siGunGu;

    @ManyToOne
    @JoinColumn(name = "crime_id")
    crime_type crime_type;

    String day;
    String spot;
    String time;
    double prtds;
}
