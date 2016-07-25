package com.log.repository;

import com.log.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthorityRepository extends JpaRepository<Authority,Long>
{
    Optional<Authority> findOneByName(String name);
}
