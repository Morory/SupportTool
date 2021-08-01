package com.support.tool.service;

import com.support.tool.domain.CountingMatter;
import com.support.tool.repository.CountingMatterRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CountingServiceImpl implements ICountingService {

    private final CountingMatterRepository countingMatterRepository;

    @Override
    public String getLatestCounting(Model model) {
        CountingMatter countingMatter = countingMatterRepository.findFirstByOrderByCountedDateDesc();
        model.addAttribute("countingMatter", countingMatter);
        return "/counting/index";
    }

    @Override
    public ResponseEntity<CountingMatter> getCountingByCountedDate(String countedDate) {
        Optional<CountingMatter> optionalCountingMatter = countingMatterRepository.findById(countedDate);
        if(!optionalCountingMatter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CountingMatter countingMatter = optionalCountingMatter.get();
        return new ResponseEntity<>(countingMatter, HttpStatus.OK);
    }
}
