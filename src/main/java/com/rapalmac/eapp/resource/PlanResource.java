package com.rapalmac.eapp.resource;

import com.rapalmac.eapp.dto.PlanDto;
import com.rapalmac.eapp.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
public class PlanResource {
    private final PlanService service;

    public PlanResource(PlanService planService) {
        this.service = planService;
    }

    @PostMapping("/plan")
    @ResponseBody
    public ResponseEntity<PlanDto> addPlan(@RequestBody PlanDto dto) {
        if (dto != null) {
            var saved = service.add(dto);
            return ResponseEntity
                    .created(URI.create("/plan/" + saved.getUuid()))
                    .body(saved);

        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/plan/{uuid}")
    public ResponseEntity<PlanDto> getPlanById(@PathVariable("uuid") String uuid) {
        if (uuid == null || uuid.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            return ResponseEntity.ok(service.findById(uuid));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/plan", produces = "application/json")
    public Set<PlanDto> listAllPlans() {
        return service.listAll();
    }
}