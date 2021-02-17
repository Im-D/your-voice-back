package com.imd.yourvoice.sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    @BeforeEach
    void setUp() {
        testRepository.save(TestEntity.builder().name("test").build());
        testRepository.save(TestEntity.builder().name("test2").build());
    }

    @Test
    void size() {
        assertThat(testRepository.findAll().size())
                .isEqualTo(2);
    }

    @Test
    void getOne() {
        String name = "test";
        TestEntity result = testRepository.getOne(1L);
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    void findByName() {
        String name = "test";
        TestEntity result = testRepository.findByName(name);
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    void queryDslTest() {
        System.out.println(testRepository.queryDslTest(TestEntity.builder().name("test").build()));
    }
}
