package com.capstone.report.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long patientId;
    private String category;
    private String surveyFormLink;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    public Reports(long id, long patientId,String category, String sfl){//,LocalDateTime created,LocalDateTime updated){
        this.id = id;
        this.patientId = patientId;
        this.category = category;
        this.surveyFormLink = sfl;
        // this.created = created;
        // this.updated = updated;
    }
}
