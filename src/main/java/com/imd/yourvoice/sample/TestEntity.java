package com.imd.yourvoice.sample;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Entity
@Table(name = "TEST")
public class TestEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public TestDTO toDto() {
        return TestDTO.builder()
                .id(id)
                .name(name)
                .build();
    }
}
