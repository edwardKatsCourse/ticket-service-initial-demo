package com.example.demo.repository;

import com.example.demo.model.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    UserSession findBySessionIdAndIsExpiredFalse(String sessionId);
}
