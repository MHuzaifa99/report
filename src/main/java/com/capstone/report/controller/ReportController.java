package com.capstone.report.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capstone.report.entity.Reports;
import com.capstone.report.entity.Response;
import com.capstone.report.repository.ReportRepo;

@CrossOrigin("*")
@RestController
@RequestMapping("/report")
public class ReportController {
    
    ReportRepo repo;
    public ReportController(ReportRepo repo){
        this.repo = repo;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> reportAdd(@RequestBody Reports report){
        Reports reportAdded = repo.save(report);
        return ResponseEntity.ok().body(new Response(true, reportAdded, "Report added successfully"));
    }

    @PostMapping("/update")
    public ResponseEntity<Response> reportUpdate(@RequestBody Reports report){
        Reports updatedReport = repo.save(report);
        return ResponseEntity.ok().body(new Response(true, updatedReport, "Report updated successfully"));
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getReports(){
        List<Reports> allreports = repo.findAll();
        return ResponseEntity.ok().body(new Response(true, allreports, "Reports fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getReport(@PathVariable long id){
        Reports report = repo.findById(id).orElse(null);
        return ResponseEntity.ok().body(new Response(true, report, "Reports fetched successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deletereport(@PathVariable long id){
        repo.deleteById(id);
        return ResponseEntity.ok().body(new Response(true, "Report deleted"));
    }

    @GetMapping("/patientId/{patientId}")
    public ResponseEntity<Response> getByPateintId(@PathVariable long patientId){
        // Reports report = repo.getByPatientId(patientId);
        return ResponseEntity.ok().body(new Response(true, repo.getByPatientId(patientId), "Report fetched successfully"));
    }
}
