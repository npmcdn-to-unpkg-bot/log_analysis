package com.log.repository;

import com.log.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AlertRepository extends JpaRepository<Alert,Long>
{

    Optional<Alert> findOneById(Long userId);

    @Query(name = "",value = "SELECT * from alert where date between :start and :end",nativeQuery = true)
    List<Alert> findByDateBetween(@Param("start")String start, @Param(("end"))String end) ;
}
