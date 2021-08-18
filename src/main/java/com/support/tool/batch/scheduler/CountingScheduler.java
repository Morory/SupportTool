package com.support.tool.batch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class CountingScheduler {

    private final Job job;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "0 10 0 * * ?")
    public void executeJob() {
        try {
            jobLauncher.run(
                    job,
                    new JobParametersBuilder()
                        .addString("datetime", LocalDateTime.now().toString())
                        .toJobParameters()
            );
        } catch (JobExecutionException e) {
            e.printStackTrace();
        }
    }
}
