package com.vastag.batch.steps;

import com.vastag.batch.domain.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoLarguraFixaStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean("leituraArquivoLargura")
    public Step leituraArquivoLarguraFixaStep(@Qualifier("arquivoReader") ItemReader<Cliente> reader, @Qualifier("arquivoWriter") ItemWriter<Object> writer) {
        return stepBuilderFactory
                .get("leituraArquivoLarguraFixaStep")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
