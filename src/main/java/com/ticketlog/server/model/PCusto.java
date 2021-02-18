package com.ticketlog.server.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pcusto")

@JsonInclude(Include.NON_NULL)
public class PCusto {

    @Id
    private UUID id;

    @PositiveOrZero(message = "Valor deve ser maior ou igual a zero")
    @NotNull(message = "Campo não deve ser vazio")
    private Double custoPorPessoa;

    @PositiveOrZero(message = "Valor deve ser maior ou igual a zero")
    @NotNull(message = "Campo não deve ser vazio")
    private Double desconto;

    @Min(value = 0, message = "Valor deve ser maior ou igual a zero")
    @NotNull(message = "Campo não deve ser vazio")
    private Long valorCorte;

    public PCusto() {
        setId(UUID.randomUUID());
        setCustoPorPessoa(0.0);
        setValorCorte(0L);
    }
}
