package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CrimeSpot extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "crimeId")
    CrimeType crimeType;

    String spot;
    int count;
    int year;
}
