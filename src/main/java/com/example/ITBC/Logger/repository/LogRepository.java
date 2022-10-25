package com.example.ITBC.Logger.repository;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.model.LogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findAllByLogType(LogType logType);
}
