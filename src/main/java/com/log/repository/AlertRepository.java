package com.log.repository;

import com.log.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AlertRepository extends JpaRepository<Alert,Long>
{

    Optional<Alert> findOneById(Long userId);
}
