package com.imd.yourvoice.common;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDTO<T> {
    private T data;
    private String isSuccess;
    private String message;
}
