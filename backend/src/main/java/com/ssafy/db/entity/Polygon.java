package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Polygon extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "dongId")
    Dong dong;

    @Column(columnDefinition="TEXT")
    String coordinates;
}