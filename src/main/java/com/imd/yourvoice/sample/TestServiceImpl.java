package com.imd.yourvoice.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public TestDTO readHello() {
        TestDTO result = testRepository.findByName("hello").toDto();
        return result == null ?
                createHello(TestDTO.builder().name("hello").build()) :
                result;
    }

    @Override
    public TestDTO createHello(TestDTO testDTO) {
        TestEntity result = testRepository.save(testDTO.toEntity());
        return result.toDto();
    }
}
