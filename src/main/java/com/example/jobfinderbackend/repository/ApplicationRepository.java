package com.example.jobfinderbackend.repository;

import com.example.jobfinderbackend.model.ApplicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel, Long> {

}
