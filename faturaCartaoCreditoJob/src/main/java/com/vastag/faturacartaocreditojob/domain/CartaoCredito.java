package com.vastag.faturacartaocreditojob.domain;

import lombok.Data;

@Data
public class CartaoCredito {
    private int numeroCartaoCredito;
    private Cliente cliente;
}
