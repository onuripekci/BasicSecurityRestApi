package com.works.customerrestapibasicsecurity.repositories;

import com.works.customerrestapibasicsecurity.entities.Fault;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaultRepository extends JpaRepository<Fault, Integer> {
}