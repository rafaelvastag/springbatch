package com.vastag.faturacartaocreditojob.step;

import com.vastag.faturacartaocreditojob.domain.FaturaCartaoCredito;
import com.vastag.faturacartaocreditojob.domain.Transacao;
import com.vastag.faturacartaocreditojob.reader.FaturaCartaoCreditoReader;
import com.vastag.faturacartaocreditojob.writer.TotalTransacoesFooterCallback;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaturaCartaoCreditoStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    public Step faturaCartaoCreditoStep(ItemStreamReader<Transacao> transacoesReader,
                                        ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> carregarDadosClienteProcessor,
                                        ItemWriter<FaturaCartaoCredito> faturaCartaoCreditoWriter,
                                        TotalTransacoesFooterCallback listener) {
        return stepBuilderFactory
                .get("faturaCartaoCreditoStep")
                .<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1)
                .reader(new FaturaCartaoCreditoReader(transacoesReader))
                .processor(carregarDadosClienteProcessor)
                .writer(faturaCartaoCreditoWriter)
                .listener(listener)
                .build();
    }
}
