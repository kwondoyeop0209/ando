package com.ssafy.db.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.dto.arrestrate.GetGuCrimeListDto;
import com.ssafy.db.dto.arrestrate.GetTotalCrimeListDto;
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
        return jpaQueryFactory.select(Projections.fields(GetTotalCrimeListDto.class, qArrestRate.year, qArrestRate.count.sum().as("count"))).from(qArrestRate).where(qArrestRate.type.eq("발생")).groupBy(qArrestRate.year).orderBy(qArrestRate.year.asc()).fetch();
    }


}
