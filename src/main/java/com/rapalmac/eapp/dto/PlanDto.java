package com.rapalmac.eapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDto {
    private String uuid;
    private String name;
    private String description;
    private Double cost;
}
