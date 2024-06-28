package com.rapalmac.eapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = {"uuid"})
public class PlanDto {
    private String uuid;
    private String name;
    private String description;
    private Double cost;
}
