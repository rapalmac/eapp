package com.rapalmac.eapp.service.impl;

import com.rapalmac.eapp.dto.PlanDto;
import com.rapalmac.eapp.model.Plan;
import com.rapalmac.eapp.repo.PlanRepo;
import com.rapalmac.eapp.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {
    public PlanRepo planRepo;

    public PlanServiceImpl(PlanRepo planRepo) {
        this.planRepo = planRepo;
    }

    @Override
    public PlanDto add(PlanDto dto) {
        var plan = Plan
                .builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        var saved = planRepo.save(plan);

        return PlanDto.builder()
                .uuid(saved.getUuid())
                .name(saved.getName())
                .description(saved.getDescription())
                .build();
    }

    @Override
    public Set<PlanDto> listAll() {
        return planRepo.findAll().stream()
                .map(p -> PlanDto
                        .builder()
                        .uuid(p.getUuid())
                        .name(p.getName())
                        .description(p.getDescription())
                        .build()
                ).collect(Collectors.toSet());
    }

    @Override
    public PlanDto findById(String uuid) {
        return planRepo.findById(uuid).map(p -> PlanDto
                        .builder()
                        .uuid(p.getUuid())
                        .name(p.getName())
                        .description(p.getDescription())
                        .build()).orElseThrow();
    }
}
