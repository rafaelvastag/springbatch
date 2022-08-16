package com.vastag.batch.steps.readers;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MultiplosArquivosClienteTransacaoReader {


    @Bean("multiResource")
    @StepScope
    public MultiResourceItemReader multiplosArquivosClienteTransacaoReaderConfig(
            @Value("#{jobParameters['arquivosClientes']}") Resource[] arquivosClientes,
            @Qualifier("arquivoMultiFormatReader") FlatFileItemReader leituraArquivoMultiplosFormatosReader) {
        return new MultiResourceItemReaderBuilder()
                .name("multiplosArquivosClienteTransacaoReader")
                .resources(arquivosClientes)
                .delegate(new ArquivoClienteTransacaoReader(leituraArquivoMultiplosFormatosReader))
                .build();
    }
}
