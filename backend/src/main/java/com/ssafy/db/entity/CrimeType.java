package com.ssafy.db.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CrimeType extends BaseEntity{
    String type;
}
