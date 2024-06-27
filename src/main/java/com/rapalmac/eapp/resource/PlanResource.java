package com.rapalmac.eapp.resource;

import com.rapalmac.eapp.dto.PlanDto;
import com.rapalmac.eapp.model.Plan;
import com.rapalmac.eapp.repo.PlanRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class PlanResource {
    private final PlanRepo planRepo;

    public PlanResource(PlanRepo planRepo) {
        this.planRepo = planRepo;
    }

    @PostMapping("/plan")
    @ResponseBody
    public ResponseEntity<PlanDto> addPlan(@RequestBody PlanDto dto) {
        if (dto != null) {
            var plan = Plan
                    .builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .build();

            var saved = planRepo.save(plan);
            return ResponseEntity
                    .created(URI.create("/plan/" + saved.getUuid()))
                    .body(PlanDto.builder()
                                .uuid(saved.getUuid())
                                .name(saved.getName())
                                .description(saved.getDescription())
                            .build());

        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/plan/{uuid}")
    public ResponseEntity<PlanDto> getPlanById(@PathVariable("uuid") String uuid) {
        if (uuid == null || uuid.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        return planRepo.findById(uuid).map(value ->
                ResponseEntity.ok(PlanDto
                    .builder()
                        .uuid(value.getUuid())
                        .name(value.getName())
                        .description(value.getDescription())
                    .build()))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping(value = "/plan", produces = "application/json")
    public List<PlanDto> listAllPlans() {
        return planRepo.findAll().stream()
                .map(p -> PlanDto
                        .builder()
                            .uuid(p.getUuid())
                            .name(p.getName())
                            .description(p.getDescription())
                        .build()
                ).toList();
    }
}