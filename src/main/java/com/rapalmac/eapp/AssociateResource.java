package com.rapalmac.eapp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AssociateResource {
    @GetMapping("/associate")
    public String getAssociates() {
        return "[{\"name\": \"Juan Perez\"}]";
    }
}