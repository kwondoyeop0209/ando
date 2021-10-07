package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CrimeDay extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "crimeId")
    CrimeType crimeType;

    String day;
    int count;
    int year;
}
