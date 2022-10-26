package com.example.ITBC.Logger.repository;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.model.LogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("SELECT l FROM Log l WHERE (:message is null or l.message = :message) and (:logType is null"
            + " or l.logType = :logType)")
    List<Log> findLogsByParam(@Param("message") String message, @Param("logType") LogType logType);
}
