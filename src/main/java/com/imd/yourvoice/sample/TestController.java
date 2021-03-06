package com.imd.yourvoice.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/hello")
    public TestDTO readHello() {
        return testService.readHello();
    }

    @PostMapping("/hello")
    public TestDTO createHello(@Valid @RequestBody TestDTO testDTO) {
        return testService.createHello(testDTO);
    }
}
