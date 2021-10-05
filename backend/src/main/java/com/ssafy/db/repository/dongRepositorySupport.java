package com.ssafy.db.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.dto.space.CountByDong;
import com.ssafy.db.entity.QArrestRate;
import com.ssafy.db.entity.QDong;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class dongRepositorySupport extends QuerydslRepositorySupport {

    QArrestRate qArrestRate = QArrestRate.arrestRate;
    QDong qDong = QDong.dong1;
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param jpaQueryFactory must not be {@literal null}.
     */
    public dongRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(QDong.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Long getDongCountInGu(Long id){
    return jpaQueryFactory.select(qDong.count()).from(qDong).where(
        qDong.siGunGu.id.eq(
        JPAExpressions.select(qDong.siGunGu.id).from(qDong).where(qDong.id.eq(id)))).fetchOne();
    }

    public Integer getSpaceCountByGuId(String type, Long guId){
        if (type.equals("cctv")) {
            return jpaQueryFactory.select(qDong.cctvCnt.sum()).from(qDong).where(qDong.siGunGu.id.eq(guId)).fetchOne();
        }
        else if (type.equals("police")) {
            return jpaQueryFactory.select(qDong.policeCnt.sum()).from(qDong).where(qDong.siGunGu.id.eq(guId)).fetchOne();
        }
        else if (type.equals("light")) {
            return jpaQueryFactory.select(qDong.lightCnt.sum()).from(qDong).where(qDong.siGunGu.id.eq(guId)).fetchOne();
        }
        else if (type.equals("guard")) {
            return jpaQueryFactory.select(qDong.guardHouseCnt.sum()).from(qDong).where(qDong.siGunGu.id.eq(guId)).fetchOne();
        }
        else {
            return jpaQueryFactory.select(qDong.barCnt.sum()).from(qDong).where(qDong.siGunGu.id.eq(guId)).fetchOne();
        }
    }

    public Long getRanking(Long id){
        return jpaQueryFactory.select(qDong.count().add(1).as("ranking"))
            .from(qDong).where(
                qDong.safetyIndex
                    .gtAll(JPAExpressions.select(qDong.safetyIndex)
                        .from(qDong)
                    .where(qDong.id.eq(id)))
            ).fetchOne();
    }

    public Long getGuRanking(Long id){
        return jpaQueryFactory.select(qDong.count().add(1).as("ranking"))
            .from(qDong).where(
                qDong.safetyIndex
                    .gtAll(JPAExpressions.select(qDong.safetyIndex)
                        .from(qDong)
                        .where(qDong.id.eq(id)))
                .and(qDong.siGunGu.id.eq(
                    JPAExpressions.select(qDong.siGunGu.id).from(qDong).where(qDong.id.eq(id))
                ))
            )
            .fetchOne();
    }

    public Long getSpaceRanking(String type, Long id){
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("cctv")) {
            builder.and(qDong.cctvCnt
                .gtAll(JPAExpressions.select(qDong.cctvCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else if (type.equals("police")) {
            builder.and(qDong.policeCnt
                .gtAll(JPAExpressions.select(qDong.policeCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else if (type.equals("light")) {
            builder.and(qDong.lightCnt
                .gtAll(JPAExpressions.select(qDong.lightCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else if (type.equals("guard")) {
            builder.and(qDong.guardHouseCnt
                .gtAll(JPAExpressions.select(qDong.guardHouseCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else {
            builder.and(qDong.barCnt
                .gtAll(JPAExpressions.select(qDong.barCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }

        return jpaQueryFactory.select(qDong.count().add(1).as("ranking"))
            .from(qDong)
            .where(builder).fetchOne();
    }

    public Double getSpaceAvg(String type){


        if (type.equals("cctv")) {
            return jpaQueryFactory.select(qDong.cctvCnt.avg()).from(qDong).fetchOne();
        }
        else if (type.equals("police")) {
            return jpaQueryFactory.select(qDong.policeCnt.avg()).from(qDong).fetchOne();
        }
        else if (type.equals("light")) {
            return jpaQueryFactory.select(qDong.lightCnt.avg()).from(qDong).fetchOne();
        }
        else if (type.equals("guard")) {
            return jpaQueryFactory.select(qDong.guardHouseCnt.avg()).from(qDong).fetchOne();
        }
        else {
            return jpaQueryFactory.select(qDong.barCnt.avg()).from(qDong).fetchOne();
        }
    }

    public Long getSpaceRankingInGu(String type, Long id) {
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("cctv")) {
            builder.and(qDong.cctvCnt
                .gtAll(JPAExpressions.select(qDong.cctvCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else if (type.equals("police")) {
            builder.and(qDong.cctvCnt
                .gtAll(JPAExpressions.select(qDong.policeCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else if (type.equals("light")) {
            builder.and(qDong.lightCnt
                .gtAll(JPAExpressions.select(qDong.lightCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else if (type.equals("guard")) {
            builder.and(qDong.guardHouseCnt
                .gtAll(JPAExpressions.select(qDong.guardHouseCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }
        else {
            builder.and(qDong.barCnt
                .gtAll(JPAExpressions.select(qDong.barCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id))));
        }

        return jpaQueryFactory.select(qDong.count().add(1).as("ranking"))
            .from(qDong)
            .where(builder.and(qDong.siGunGu.id.eq(
                JPAExpressions.select(qDong.siGunGu.id).from(qDong).where(qDong.id.eq(id))
            ))).fetchOne();
    }

    public List<CountByDong> get5List(Long start, Long id){
        return jpaQueryFactory.select(Projections.fields(CountByDong.class, qDong.dong.as("dongname"),qDong.cctvCnt.as("count")))
            .from(qDong)
            .where(qDong.siGunGu.id.eq(
                JPAExpressions
                    .select(qDong.siGunGu.id)
                    .from(qDong)
                    .where(qDong.id.eq(id)))
            ).orderBy(qDong.cctvCnt.desc()).offset(start).limit(5).fetch();

    }

    public List<CountByDong> get5List(String type, Long start, Long id) {

        if (type.equals("cctv")) {
            return jpaQueryFactory.select(Projections.fields(CountByDong.class, qDong.dong.as("dongname"),qDong.cctvCnt.as("count")))
                .from(qDong)
                .where(qDong.siGunGu.id.eq(
                    JPAExpressions
                        .select(qDong.siGunGu.id)
                        .from(qDong)
                        .where(qDong.id.eq(id)))
                ).orderBy(qDong.cctvCnt.desc()).offset(start).limit(5).fetch();
        }
        else if (type.equals("police")) {
            return jpaQueryFactory.select(Projections.fields(CountByDong.class, qDong.dong.as("dongname"),qDong.policeCnt.as("count")))
                .from(qDong)
                .where(qDong.siGunGu.id.eq(
                    JPAExpressions
                        .select(qDong.siGunGu.id)
                        .from(qDong)
                        .where(qDong.id.eq(id)))
                ).orderBy(qDong.policeCnt.desc()).offset(start).limit(5).fetch();
        }
        else if (type.equals("light")) {
            return jpaQueryFactory.select(Projections.fields(CountByDong.class, qDong.dong.as("dongname"),qDong.lightCnt.as("count")))
                .from(qDong)
                .where(qDong.siGunGu.id.eq(
                    JPAExpressions
                        .select(qDong.siGunGu.id)
                        .from(qDong)
                        .where(qDong.id.eq(id)))
                ).orderBy(qDong.lightCnt.desc()).offset(start).limit(5).fetch();
        }
        else if (type.equals("guard")) {
            return jpaQueryFactory.select(Projections.fields(CountByDong.class, qDong.dong.as("dongname"),qDong.guardHouseCnt.as("count")))
                .from(qDong)
                .where(qDong.siGunGu.id.eq(
                    JPAExpressions
                        .select(qDong.siGunGu.id)
                        .from(qDong)
                        .where(qDong.id.eq(id)))
                ).orderBy(qDong.guardHouseCnt.desc()).offset(start).limit(5).fetch();
        }
        else {
            return jpaQueryFactory.select(Projections.fields(CountByDong.class, qDong.dong.as("dongname"),qDong.barCnt.as("count")))
                .from(qDong)
                .where(qDong.siGunGu.id.eq(
                    JPAExpressions
                        .select(qDong.siGunGu.id)
                        .from(qDong)
                        .where(qDong.id.eq(id)))
                ).orderBy(qDong.barCnt.desc()).offset(start).limit(5).fetch();
        }
    }

    public Long getSpaceRankingDistinct(String type, Long id){
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("cctv")) {
            return jpaQueryFactory.select(qDong.cctvCnt.countDistinct().add(1).as("ranking"))
                .from(qDong)
                .where(qDong.cctvCnt
                    .gtAll(JPAExpressions.select(qDong.cctvCnt)
                        .from(qDong)
                        .where(qDong.id.eq(id)))).fetchOne();
        }
        else if (type.equals("police")) {
            return jpaQueryFactory.select(qDong.policeCnt.countDistinct().add(1).as("ranking"))
                .from(qDong)
            .where(qDong.policeCnt
                .gtAll(JPAExpressions.select(qDong.policeCnt)
                    .from(qDong)
                    .where(qDong.id.eq(id)))).fetchOne();
        }
        else if (type.equals("light")) {
            return jpaQueryFactory.select(qDong.lightCnt.countDistinct().add(1).as("ranking"))
                .from(qDong)
                .where(qDong.lightCnt
                    .gtAll(JPAExpressions.select(qDong.lightCnt)
                        .from(qDong)
                        .where(qDong.id.eq(id)))).fetchOne();
        }
        else if (type.equals("guard")) {
            return jpaQueryFactory.select(qDong.guardHouseCnt.countDistinct().add(1).as("ranking"))
                .from(qDong)
                .where(qDong.guardHouseCnt
                    .gtAll(JPAExpressions.select(qDong.guardHouseCnt)
                        .from(qDong)
                        .where(qDong.id.eq(id)))).fetchOne();
        }
        else {
            return jpaQueryFactory.select(qDong.barCnt.countDistinct().add(1).as("ranking"))
                .from(qDong)
                .where(qDong.barCnt
                    .gtAll(JPAExpressions.select(qDong.barCnt)
                        .from(qDong)
                        .where(qDong.id.eq(id)))).fetchOne();
        }
    }
}
