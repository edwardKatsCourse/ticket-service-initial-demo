package com.example.demo.repository;

import com.example.demo.model.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Integer> {
}
