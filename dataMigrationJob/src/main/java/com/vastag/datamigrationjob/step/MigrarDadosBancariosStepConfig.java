package com.vastag.datamigrationjob.step;

import com.vastag.datamigrationjob.domain.DadosBancarios;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrarDadosBancariosStepConfig {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migrarDadosBancariosStep(@Qualifier("arquivoDadosBancariosReader") ItemReader<DadosBancarios> arquivoDadosBancariosReader, @Qualifier("bancoDadosBancariosWriter") ItemWriter<DadosBancarios> bancoDadosBancariosWriter) {

        return stepBuilderFactory
                .get("migrarDadosBancariosStep")
                .<DadosBancarios, DadosBancarios>chunk(10000)
                .reader(arquivoDadosBancariosReader)
                .writer(bancoDadosBancariosWriter)
                .build();
    }
}
