package com.log.repository;

import com.log.domain.Category;
import com.log.domain.LogLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category,Long>

{

    Optional<Category> findByCriticite(LogLevel level) ;
}
