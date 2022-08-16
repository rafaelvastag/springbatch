package com.vastag.batch.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeOlaStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean("hello")
    public Step printHelloStep(Tasklet tasklet) {
        return stepBuilderFactory
                .get("printOlaStep")
                .tasklet(tasklet)
                .build();
    }
}
