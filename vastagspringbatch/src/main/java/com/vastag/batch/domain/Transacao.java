package com.vastag.batch.domain;

import lombok.Data;

@Data
public class Transacao {

    private String id;
    private String descricao;
    private Double valor;
}
