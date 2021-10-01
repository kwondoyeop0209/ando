package com.ssafy.db.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.dto.arrestrate.GetGuCrimeListDto;
import com.ssafy.db.dto.arrestrate.GetTotalCrimeListDto;
import com.ssafy.db.dto.arrestrate.TopGuListDto;
import com.ssafy.db.entity.ArrestRate;
import com.ssafy.db.entity.QArrestRate;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArrestRateRepositorySupport extends QuerydslRepositorySupport {

    QArrestRate qArrestRate = QArrestRate.arrestRate;
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param jpaQueryFactory must not be {@literal null}.
     */
    public ArrestRateRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(ArrestRate.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<GetTotalCrimeListDto> findSeoulCrime() {
        return jpaQueryFactory.select(Projections.fields(GetTotalCrimeListDto.class, qArrestRate.year, qArrestRate.count.sum().as("count"))).from(qArrestRate).where(qArrestRate.type.eq("발생")).groupBy(qArrestRate.year,qArrestRate.type).orderBy(qArrestRate.year.asc()).fetch();
    }

    public List<GetGuCrimeListDto> findGuCrime(Long id,int year){
        return jpaQueryFactory.select(Projections.fields(GetGuCrimeListDto.class,qArrestRate.crimeType.type.as("crimeType") ,qArrestRate.count,qArrestRate.crimeType.id.as("crimeTypeId"))).from(qArrestRate).where(qArrestRate.siGunGu.id.eq(id),qArrestRate.type.eq("발생"),qArrestRate.year.eq(year)).orderBy(qArrestRate.crimeType.id.asc()).fetch();
    }

    public int getTotalCount(String type,int year){
        return jpaQueryFactory.select(qArrestRate.count.sum().as("count")).from(qArrestRate).where(qArrestRate.type.eq(type),qArrestRate.year.eq(year)).fetchOne();
    }
    public int getGuCount(String type,int year,Long guId){
        return jpaQueryFactory.select(qArrestRate.count.sum().as("count")).from(qArrestRate).where(qArrestRate.year.eq(year),qArrestRate.siGunGu.id.eq(guId),qArrestRate.type.eq(type)).fetchOne();
    }

    public List<TopGuListDto> getTopGu(String type){
        return jpaQueryFactory.select(Projections.fields(TopGuListDto.class,qArrestRate.siGunGu.gu.as("gu"),qArrestRate.count.sum().as("count"))).from(qArrestRate).where(qArrestRate.type.eq(type),qArrestRate.year.eq(2020)).groupBy(qArrestRate.siGunGu).orderBy(qArrestRate.count.sum().desc()).limit(5).fetch();
    }
}
