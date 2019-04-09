package com.dental.project.repository;

import com.dental.project.model.company.CompanyType;
import com.dental.project.model.company.CompanyTypeName;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {
    Optional<CompanyType> findByName(CompanyTypeName companyTypeName);


}
