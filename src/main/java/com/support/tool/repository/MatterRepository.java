package com.support.tool.repository;

import com.support.tool.domain.Matter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatterRepository extends JpaRepository<Matter, Integer> {

    Page<Matter> findAllByStatus(Pageable pageable, char status);

    @Query(value = "SELECT COUNT(*) AS dailyTotal                                                 "
                 + "     , COUNT(CASE WHEN complete_status = 'N' THEN 1 END) AS uncompletedAmount "
                 + "     , COUNT(CASE WHEN complete_status = 'Y' THEN 1 END) AS completedAmount   "
                 + "     , COUNT(CASE WHEN complete_status = 'E' THEN 1 END) AS etcAmount         "
                 + "FROM matter                                                                   "
                 + "WHERE status = 'Y'                                                            "
                 + "AND   created_date BETWEEN CONCAT(:countingDate, ' 00:00:00') AND CONCAT(:countingDate, ' 23:59:59')"
            , nativeQuery = true)
    ICountingMatter findCountingMatter(@Param("countingDate") String countingDate);

    interface ICountingMatter {
        int getDailyTotal();
        int getUncompletedAmount();
        int getCompletedAmount();
        int getEtcAmount();
    }
}
