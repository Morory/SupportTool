package com.support.tool.repository;

import com.support.tool.domain.CountingMatter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountingMatterRepository extends JpaRepository<CountingMatter, String> {

    CountingMatter findFirstByOrderByCountedDateDesc();

}
