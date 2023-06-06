package com.capstone.report.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.report.entity.Reports;
import com.capstone.report.repository.ReportRepo;

@RestController
@RequestMapping("/report")
public class ReportController {
    
    ReportRepo repo;
    public ReportController(ReportRepo repo){
        this.repo = repo;
    }

    @PostMapping("/add")
    public ResponseEntity reportAdd(@RequestBody Reports report){
        return ResponseEntity.ok().body(repo.save(report));
    }

    @PostMapping("/update")
    public ResponseEntity reportUpdate(@RequestBody Reports report){
        return ResponseEntity.ok().body(repo.save(report));
    }

    @GetMapping("/list")
    public ResponseEntity getReports(){
        return ResponseEntity.ok().body(repo.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletereport(@PathVariable long id){
        repo.deleteById(id);
        return ResponseEntity.ok().body("Report deleted");
    }
}
