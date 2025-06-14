package com.PhishingScan.email.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "phishing_scan")
public class Phishing_scan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String email_text;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String explanation;

    @Column(nullable = false)
    private String result; // example: "PHISHING" or "SAFE"

    @Column(nullable = false)
    private LocalDateTime scanned_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;






}
