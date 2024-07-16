package com.api.teastore.dto;

import lombok.Data;

@Data
public class TeaCreateDto {
    private String name;

    private String description;

    private Long price;

    private String country;
}
