package com.works.customerrestapibasicsecurity.repositories;

import com.works.customerrestapibasicsecurity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}