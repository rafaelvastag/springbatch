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
public class LeituraArquivoLarguraFixaReader {

    @StepScope
    @Bean("arquivoReader")
    public FlatFileItemReader<Cliente> leituraArquivoLarguraFixaReader(@Value("#{jobParameters['arquivoClientes']}") Resource arquivo) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoLarguraFixaReader")
                .resource(arquivo)
                .fixedLength()
                .columns(new Range[]{new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43)})
                .names(new String[]{"nome", "sobrenome", "idade", "email"})
                .targetType(Cliente.class)
                .build();
    }

}
