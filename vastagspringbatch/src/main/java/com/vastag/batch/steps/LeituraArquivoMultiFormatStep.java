package com.vastag.batch.steps;

import com.vastag.batch.domain.Cliente;
import com.vastag.batch.steps.readers.ArquivoClienteTransacaoReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoMultiFormatStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean("leituraArquivoMultiFormat")
    public Step leituraArquivoMultiFormatStep(@Qualifier("multiResource") MultiResourceItemReader<Cliente> reader, @Qualifier("arquivoWriter") ItemWriter<Object> writer) {
        return stepBuilderFactory
                .get("leituraArquivoMultiplosFormatosStep")
                .chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
