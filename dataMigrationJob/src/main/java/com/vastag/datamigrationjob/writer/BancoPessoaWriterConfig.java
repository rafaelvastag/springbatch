package com.vastag.datamigrationjob.writer;

import com.vastag.datamigrationjob.domain.Pessoa;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class BancoPessoaWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Pessoa> bancoPessoaWriter(
            @Qualifier("appDataSource") DataSource dataSource
    ) {
        return new JdbcBatchItemWriterBuilder<Pessoa>()
                .dataSource(dataSource)
                .sql("INSERT INTO pessoa (id, nome, email,data_nascimento, idade) VALUES (?,?,?,?,?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<Pessoa> itemPreparedStatementSetter() {
        return new ItemPreparedStatementSetter<Pessoa>() {
            @Override
            public void setValues(Pessoa item, PreparedStatement ps) throws SQLException {
                ps.setInt(1, item.getId());
                ps.setString(2, item.getNome());
                ps.setString(3, item.getEmail());
                ps.setDate(4, new Date(item.getDataNascimento().getTime()));
                ps.setInt(5, item.getIdade());
            }
        };
    }
}
