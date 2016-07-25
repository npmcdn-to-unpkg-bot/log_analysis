package com.log.repository;

import com.log.domain.LogFile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LogFileRepository extends JpaRepository<LogFile,Long>
{
}
