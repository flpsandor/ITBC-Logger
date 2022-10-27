package com.example.ITBC.Logger.repository;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.model.LogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("SELECT l FROM Log l WHERE (:message is null or l.message = :message) and (:logType is null"
            + " or l.logType = :logType) and (:dateFrom is null or l.logDate >:dateFrom) and (:dateTo is null or l.logDate <:dateTo)")
    List<Log> findLogsByParam(@Param("message") String message, @Param("logType") LogType logType, @Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);
}
