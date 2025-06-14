package com.PhishingScan.email.repository;

import com.PhishingScan.email.entity.Phishing_scan;
import com.PhishingScan.email.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhishingScanRepository extends JpaRepository<Phishing_scan, Integer> {

    List<Phishing_scan> findByUsers(Users user);

}
