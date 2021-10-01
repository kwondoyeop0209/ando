package com.ssafy.db.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Gu extends BaseEntity{
    String gu;
    double area;
}
