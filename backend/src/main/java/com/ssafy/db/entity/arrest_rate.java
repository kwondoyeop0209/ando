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
public class arrest_rate extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "gu_id")
    gu siGunGu;

    @ManyToOne
    @JoinColumn(name = "crime_id")
    crime_type crime_type;

    int generation;
    int arrest;
    int year;
}
