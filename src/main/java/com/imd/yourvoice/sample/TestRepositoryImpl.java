package com.imd.yourvoice.sample;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestRepositoryImpl implements TestRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public TestDTO queryDslTest(TestEntity testEntity) {
        TestEntity result = queryFactory.selectFrom(QTestEntity.testEntity)
                .where(QTestEntity.testEntity.name.eq(testEntity.getName()))
                .fetchOne();

        return result.toDto();
    }
}
