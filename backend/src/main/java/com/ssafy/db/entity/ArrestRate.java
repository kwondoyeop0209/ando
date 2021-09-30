package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ArrestRate extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "guId")
    Gu siGunGu;

    @ManyToOne
    @JoinColumn(name = "crimeId")
    CrimeType crimeType;

    String type;
    int count;
    int year;
}