package com.imd.yourvoice.sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private TestRepository testRepository;

    private TestService testService;

    @BeforeEach
    void setUp() {
        testService = new TestServiceImpl(testRepository);
    }

    @Test
    void readHello() {
        when(testRepository.findByName("hello"))
                .thenReturn(TestDTO.builder().id(1L).name("hello").build().toEntity());
        assertThat(testService.readHello())
                .isEqualTo(TestDTO.builder().id(1L).name("hello").build());
    }

    @ParameterizedTest
    @MethodSource
    void createHello(TestDTO testDTO, TestDTO expectedDTO) {
        when(testRepository.save(any(TestEntity.class)))
                .thenAnswer(invocation -> {
                    TestEntity result = invocation.getArgument(0);
                    return TestEntity.builder().id(1L).name(result.getName()).build();
                });

        assertThat(testService.createHello(testDTO))
                .isEqualTo(expectedDTO);
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> createHello() {
        return Stream.of(
                Arguments.arguments(
                        TestDTO.builder().name("hello").build(),
                        TestDTO.builder().id(1L).name("hello").build()
                )
        );
    }
}
