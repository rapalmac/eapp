package com.rapalmac.eapp.repo;

import com.rapalmac.eapp.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepo extends JpaRepository<Plan, String> {
}
