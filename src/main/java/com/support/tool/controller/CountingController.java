package com.support.tool.controller;

import com.support.tool.domain.CountingMatter;
import com.support.tool.service.ICountingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/counting")
@AllArgsConstructor
@Slf4j
public class CountingController {

    private final ICountingService countingService;

    @GetMapping("/")
    public String index(Model model) {
        log.info("Counting Index called ");
        return countingService.getLatestCounting(model);
    }

    @GetMapping("/show/{countedDate}")
    public ResponseEntity<CountingMatter> show(@PathVariable String countedDate) {
        log.info("Counting Show called");
        return countingService.getCountingByCountedDate(countedDate);
    }
}
