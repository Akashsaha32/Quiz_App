package com.example.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main.model.Result;

@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {

}
