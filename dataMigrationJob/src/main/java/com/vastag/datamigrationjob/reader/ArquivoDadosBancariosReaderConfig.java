package com.vastag.datamigrationjob.reader;

import com.vastag.datamigrationjob.domain.DadosBancarios;
import com.vastag.datamigrationjob.domain.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import java.util.Date;


@Configuration
public class ArquivoDadosBancariosReaderConfig {

    @Bean
    public FlatFileItemReader<DadosBancarios> arquivoDadosBancariosReader(){

        return new FlatFileItemReaderBuilder<DadosBancarios>()
                .name("arquivoPessoaReader")
                .resource(new FileSystemResource("files/dados_bancarios.csv"))
                .delimited()
                .names("pessoaId","agencia","conta","banco","id")
                .addComment("--")
                .targetType(DadosBancarios.class)
                .build();
    }
}
