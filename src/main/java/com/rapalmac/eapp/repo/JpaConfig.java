package com.rapalmac.eapp.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.rapalmac.*"})
public class JpaConfig {
}
