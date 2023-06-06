package com.capstone.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.report.entity.Reports;

public interface ReportRepo extends JpaRepository<Reports, Long> {
    
}
