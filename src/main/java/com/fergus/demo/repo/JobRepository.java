package com.fergus.demo.repo;

import com.fergus.demo.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
}
