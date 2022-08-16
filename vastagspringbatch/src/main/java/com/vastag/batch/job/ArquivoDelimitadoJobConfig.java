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

@Configuration
@EnableBatchProcessing
public class ArquivoDelimitadoJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job arquivoDelimitadoJob(@Qualifier("leituraArquivoDelimitado") Step step){
        return jobBuilderFactory.get("arquivoDelimitadoJob")
                .start( step)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
