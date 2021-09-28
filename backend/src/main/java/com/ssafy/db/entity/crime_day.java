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
public class crime_day extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "crime_id")
    crime_type crime_type;

    String type;
    String day;
    int count;
}
