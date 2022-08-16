package com.vastag.batch.steps.writers;

import com.vastag.batch.domain.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoLarguraFixaWriter {

    @Bean("arquivoWriter")
    public ItemWriter<Object> leituraArquivoLarguraFixaWriter() {
        return items -> items.forEach(System.out::println);
    }

}
