package com.log.repository;

import com.log.domain.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AlertRepository extends JpaRepository<Alert,Long>
{

    Optional<Alert> findOneById(Long userId);

//    @Query(name = "",value = "SELECT * from alert where date between :start and :end",nativeQuery = true)
    Page<Alert> findByDateBetween(LocalDate start, LocalDate end,Pageable pageable) ;
}
