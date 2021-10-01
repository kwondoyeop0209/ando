package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CrimeTime extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "crimeId")
    CrimeType crimeType;

    String time;
    int count;
    
}
