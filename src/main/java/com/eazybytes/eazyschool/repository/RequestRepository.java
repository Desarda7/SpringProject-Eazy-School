package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByStatus(Request.Status status);
}