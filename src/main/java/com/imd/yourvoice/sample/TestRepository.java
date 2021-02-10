package com.imd.yourvoice.sample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
    TestEntity findByName(String name);
}
