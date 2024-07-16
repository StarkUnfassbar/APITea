package com.api.teastore.repository;

import com.api.teastore.models.Tea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaRepository extends JpaRepository<Tea, Long> {
}
