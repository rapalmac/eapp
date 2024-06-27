package com.rapalmac.eapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    private String name;
    @Column(length = 255, nullable = false)
    private String description;

    @Column(precision = 12, scale = 4)
    private BigDecimal cost;
}
