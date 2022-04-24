package com.example.jobfinderbackend.repository;

import com.example.jobfinderbackend.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobModel, Long> {
    List<JobModel> findAllByTitleStartsWith(String title);
    List<JobModel> findAllByCreatedBy(Long id);
}
