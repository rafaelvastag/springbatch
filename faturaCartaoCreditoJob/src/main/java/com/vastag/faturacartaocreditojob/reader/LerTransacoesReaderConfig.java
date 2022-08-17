package com.vastag.faturacartaocreditojob.reader;

import com.vastag.faturacartaocreditojob.domain.CartaoCredito;
import com.vastag.faturacartaocreditojob.domain.Cliente;
import com.vastag.faturacartaocreditojob.domain.Transacao;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class LerTransacoesReaderConfig {

    public JdbcCursorItemReader<Transacao> transacoesReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Transacao>()
                .name("transacoesReader")
                .dataSource(dataSource)
                .sql("select * from transacao join cartao_credito using (numero_cartao_credito) order by numero_cartao_credito")
                .rowMapper(rowMapperTransacao())
                .build();
    }

    private RowMapper<Transacao> rowMapperTransacao() {
        return new RowMapper<Transacao>() {

            @Override
            public Transacao mapRow(ResultSet rs, int rowNum) throws SQLException {
                CartaoCredito cartaoCredito = new CartaoCredito();
                cartaoCredito.setNumeroCartaoCredito(rs.getInt("numero_cartao_credito"));
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cliente"));
                cartaoCredito.setCliente(cliente);

                Transacao transacao = new Transacao();
                transacao.setId(rs.getInt("id"));
                transacao.setCartaoCredito(cartaoCredito);
                transacao.setData(rs.getDate("data"));
                transacao.setValor(rs.getDouble("valor"));
                transacao.setDescricao(rs.getString("descricao"));

                return transacao;
            }

        };
    }
}
