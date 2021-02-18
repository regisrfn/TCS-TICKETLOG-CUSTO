package com.ticketlog.server.dao;

import java.util.List;
import java.util.UUID;

import com.ticketlog.server.model.PCusto;

public interface PCustoDao {
    PCusto insertParametroCusto(PCusto pCusto);

    boolean deleteParametroCustoById(UUID id);

    List<PCusto> getAll();

    PCusto getParametroCusto(UUID id);

    PCusto updateParametroCusto(UUID id, PCusto pCusto);
}