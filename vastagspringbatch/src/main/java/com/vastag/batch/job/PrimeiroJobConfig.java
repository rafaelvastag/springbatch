package com.vastag.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class PrimeiroJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job printHelloJob(@Qualifier("hello") Step imprime) {
        return jobBuilderFactory
                .get("imprimeOlaJob")
                .start(imprime)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
