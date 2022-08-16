package com.vastag.batch.steps.readers;

import com.vastag.batch.domain.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoMultiFormatReader {

    @StepScope
    @Bean("arquivoMultiFormatReader")
    public FlatFileItemReader<Cliente> leituraArquivoDelimitadoFixaReader
            (@Value("#{jobParameters['arquivoClientesMultiFormat']}") Resource arquivo,
             LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoMultiFormatReader")
                .resource(arquivo)
                .lineMapper(lineMapper)
                .build();
    }

}
