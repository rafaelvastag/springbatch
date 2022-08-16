package com.vastag.batch.steps.readers;

import com.vastag.batch.domain.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoDelimitadoReader {

    @StepScope
    @Bean("arquivoDelimitadoReader")
    public FlatFileItemReader<Cliente> leituraArquivoDelimitadoFixaReader(@Value("#{jobParameters['arquivoClientesDelimitado']}") Resource arquivo) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoDelimitadoReader")
                .resource(arquivo)
                .delimited()
                .names(new String[]{"nome", "sobrenome", "idade", "email"})
                .targetType(Cliente.class)
                .build();
    }

}
