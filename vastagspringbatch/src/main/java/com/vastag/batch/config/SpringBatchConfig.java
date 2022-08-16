package com.vastag.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@EnableBatchProcessing
@Configuration
public class SpringBatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job imprimeParImparJob() {
        return jobBuilderFactory.get("imprimeParImparJob").start(imprimeParImparStep()).incrementer(new RunIdIncrementer()).build();
    }

    private Step imprimeParImparStep() {
        return stepBuilderFactory.get("imprimeParImparStep")
                .<Integer, String>chunk(999)
                .reader(contaAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeNumero())
                .build();
    }

    private ItemWriter<String> imprimeNumero() {
        return itens -> itens.forEach(System.out::println);
    }

    private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<Integer, String>
                (item -> item % 2 == 0 ? String.format("Item %s é par", item)
                        : String.format("Item %s é impar", item));
    }

    private IteratorItemReader<Integer> contaAteDezReader() {
        List numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return new IteratorItemReader<Integer>(numeros.iterator());
    }
}
