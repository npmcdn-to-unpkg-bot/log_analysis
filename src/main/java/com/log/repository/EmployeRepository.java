package com.log.repository;

import com.log.domain.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeRepository extends JpaRepository<Employe,Long>
{

    Optional<Employe> findOneByUsername(String login);

    Optional<Employe> findOneById(Long userId);
}
