package com.ticketlog.server.repository;

import java.util.List;
import java.util.UUID;

import com.ticketlog.server.dao.PCustoDao;
import com.ticketlog.server.dao.JpaDao;
import com.ticketlog.server.model.PCusto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParametroCustoRepository implements PCustoDao {

    private JpaDao jpaDataAccess;

    @Autowired
    public ParametroCustoRepository(JpaDao jpaDataAccess, JdbcTemplate jdbcTemplate) {
        this.jpaDataAccess = jpaDataAccess;
    }

    @Override
    public PCusto insertParametroCusto(PCusto ParametroCusto) {
        return jpaDataAccess.save(ParametroCusto);
    }

    @Override
    public boolean deleteParametroCustoById(UUID id) {
        try {
            jpaDataAccess.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PCusto> getAll() {
        return jpaDataAccess.findAll();
    }

    @Override
    public PCusto getParametroCusto(UUID id) {
        return jpaDataAccess.findById(id).orElse(null);
    }

    @Override
    public PCusto updateParametroCusto(UUID id, PCusto ParametroCusto) {
        ParametroCusto.setId(id);
        return jpaDataAccess.save(ParametroCusto);
    }
}