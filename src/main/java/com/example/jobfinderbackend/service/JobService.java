package com.example.jobfinderbackend.service;

import com.example.jobfinderbackend.model.JobModel;

import java.util.List;

public interface JobService {
    JobModel save(JobModel job);
    JobModel findById(Long id);
    List<JobModel> findByTitleStartsWith(String title);
    List<JobModel> findAllByCreatedBy(Long id);
}
