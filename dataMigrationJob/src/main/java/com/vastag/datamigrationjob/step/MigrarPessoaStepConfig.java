package com.vastag.datamigrationjob.step;

import com.vastag.datamigrationjob.domain.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrarPessoaStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migrarPessoaStep(@Qualifier("arquivoPessoaReader") ItemReader<Pessoa> arquivoPessoaReader
            , ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter
            , FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {

        return stepBuilderFactory
                .get("migrarPessoaStep")
                .<Pessoa, Pessoa>chunk(10000)
                .reader(arquivoPessoaReader)
                .writer(pessoaClassifierWriter)
                .stream(arquivoPessoasInvalidasWriter)
                .build();
    }
}
