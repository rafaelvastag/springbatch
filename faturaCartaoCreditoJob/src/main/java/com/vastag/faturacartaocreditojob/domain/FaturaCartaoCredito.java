package com.vastag.faturacartaocreditojob.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FaturaCartaoCredito {
    private Cliente cliente;
    private CartaoCredito cartaoCredito;
    private List<Transacao> transacoes = new ArrayList<>();
    private Double total = 0.0;

    public Double getTotal() {

        transacoes.forEach(t -> total += t.getValor());
        return total;
    }
}
