package com.imd.yourvoice.sample;

import lombok.*;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDTO {
    private Long id;
    @Size(min = 1, max = 5)
    private String name;

    public TestEntity toEntity() {
        return TestEntity.builder()
                .id(id)
                .name(name)
                .build();
    }
}
