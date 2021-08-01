package com.support.tool.service;

import com.support.tool.domain.CountingMatter;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface ICountingService {

    String getLatestCounting(Model model);

    ResponseEntity<CountingMatter> getCountingByCountedDate(String countedDate);
}
