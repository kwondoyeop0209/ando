package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RiskIndex extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "guId")
    Gu siGunGu;

    @ManyToOne
    @JoinColumn(name = "crimeId")
    CrimeType crime_type;

    String day;
    String spot;
    String time;
    double prtds;
}
