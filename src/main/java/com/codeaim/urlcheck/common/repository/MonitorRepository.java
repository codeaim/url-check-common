package com.codeaim.urlcheck.common.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codeaim.urlcheck.common.model.Monitor;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long>
{
    @Query(" SELECT m " +
            "FROM Monitor m " +
            "WHERE ((m.state = com.codeaim.urlcheck.common.model.State.WAITING " +
            "           AND m.audit <= :currentDate) " +
            "       OR (m.state = com.codeaim.urlcheck.common.model.State.ELECTED " +
            "           AND m.locked <= :currentDate)) " +
            "   AND ((:isClustered = false) " +
            "       OR (m.confirming = false)" +
            "       OR  (:isClustered = true " +
            "           AND m.auditor <> :auditor ))"
    )
    Page<Monitor> findElectable(
            @Param("auditor") String auditor,
            @Param("isClustered") boolean isClustered,
            @Param("currentDate") LocalDateTime currentDate,
            Pageable pageRequest
    );

    Page<Monitor> findByUserId(Long userId, Pageable pageRequest);
}
