package com.example.jobfinderbackend.service;

import com.example.jobfinderbackend.model.ApplicationModel;

public interface ApplicationService {
    ApplicationModel saveApplication(ApplicationModel applicationModel);
    ApplicationModel findApplicationById(Long id);
    ApplicationModel findApplicationByJobId(Long jobId);
    ApplicationModel findApplicationByUserId(Long userId);
//    ApplicationModel findApplicationByJobIdAndUserId(Long jobId, Long userId);
//    void deleteApplication(Long id);
}
