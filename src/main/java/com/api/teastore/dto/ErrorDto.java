package com.api.teastore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ErrorDto {
    private String message;
}
