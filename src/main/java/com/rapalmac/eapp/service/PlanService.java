package com.rapalmac.eapp.service;

import com.rapalmac.eapp.dto.PlanDto;

import java.util.Set;

public interface PlanService {
    PlanDto add(PlanDto plan);
    Set<PlanDto> listAll();
    PlanDto findById(String id);
}
