package com.support.tool.batch.job;

import com.support.tool.domain.CountingMatter;
import com.support.tool.repository.CountingMatterRepository;
import com.support.tool.repository.MatterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
@AllArgsConstructor
@Configuration
public class CountingJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MatterRepository matterRepository;
    private final CountingMatterRepository countingMatterRepository;

    // JobBuilderFactory 를 통해서 countingJob 을 생성
    @Bean
    public Job countingJob() {
        return jobBuilderFactory.get("countingJob")
                .start(countingStep())
                .build();
    }

    //  StepBuilderFactory 를 통해서 countingStep 을 생성
    @Bean
    public Step countingStep() {
        return stepBuilderFactory.get("countingStep")
                .tasklet(countingTasklet())
                .build();
    }

    // step 중 tasklet 실행
    @Bean
    @StepScope
    public Tasklet countingTasklet() {
        return ((contribution, chunkContext) -> {
            log.info("Tasklet started");
            Calendar cl = Calendar.getInstance();
            cl.add(Calendar.DATE, -1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String countingDate = sdf.format(cl.getTime());

            MatterRepository.ICountingMatter iCountingMatter = matterRepository.findCountingMatter(countingDate);
            CountingMatter countingMatter =
                    CountingMatter.builder()
                    .countedDate(countingDate.replace("-", ""))
                    .dailyTotal(iCountingMatter.getDailyTotal())
                    .uncompletedAmount(iCountingMatter.getUncompletedAmount())
                    .completedAmount(iCountingMatter.getCompletedAmount())
                    .etcAmount(iCountingMatter.getEtcAmount())
                    .build();

            countingMatterRepository.save(countingMatter);
            return RepeatStatus.FINISHED;
        });
    }


}
