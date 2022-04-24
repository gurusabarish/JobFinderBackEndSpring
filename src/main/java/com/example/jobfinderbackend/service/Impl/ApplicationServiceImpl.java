package com.example.jobfinderbackend.service.Impl;

import com.example.jobfinderbackend.model.ApplicationModel;
import com.example.jobfinderbackend.repository.ApplicationRepository;
import com.example.jobfinderbackend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public ApplicationModel saveApplication(ApplicationModel applicationModel) {
        return null;
    }

    @Override
    public ApplicationModel findApplicationById(Long id) {
        return null;
    }

    @Override
    public ApplicationModel findApplicationByJobId(Long jobId) {
        return null;
    }

    @Override
    public ApplicationModel findApplicationByUserId(Long userId) {
        return null;
    }
}
