package com.rapalmac.eapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AssociateResource {
    @GetMapping(value = "/associate", produces = "application/json")
    public String getAssociates() {
        return "[{\"name\": \"Juan Perez\"}]";
    }
}