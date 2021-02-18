package com.ticketlog.server.service;

import java.util.List;
import java.util.UUID;

import com.ticketlog.server.dao.PCustoDao;
import com.ticketlog.server.exception.ApiRequestException;
import com.ticketlog.server.model.PCusto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ParametroCustoService {

    private PCustoDao pCustoDao;

    @Autowired
    public ParametroCustoService(PCustoDao pCustoDao) {
        this.pCustoDao = pCustoDao;
    }

    public PCusto saveParametroCusto(PCusto pCusto) {
        try {
            return pCustoDao.insertParametroCusto(pCusto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiRequestException("Custo não pode ser salvo", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<PCusto> getAllParametroCustos() {
        try {
            return pCustoDao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiRequestException("Listagem dos custos não pode ser efetuada",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public PCusto getParametroCustoById(String id) {
        try {
            UUID pCustoId = UUID.fromString(id);
            PCusto pCusto = pCustoDao.getParametroCusto(pCustoId);
            if (pCusto == null)
                throw new ApiRequestException("Parametro não encontrado", HttpStatus.NOT_FOUND);
            return pCusto;
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException("Formato de id invalido", HttpStatus.BAD_REQUEST);
        }

    }

    public boolean deleteParametroCustoById(String id) {
        try {
            UUID pCustoId = UUID.fromString(id);
            boolean ok = pCustoDao.deleteParametroCustoById(pCustoId);
            if (!ok)
                throw new ApiRequestException("Parametro não encontrado", HttpStatus.NOT_FOUND);
            return ok;
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException("Formato de id invalido", HttpStatus.BAD_REQUEST);
        }
    }

    public PCusto updateParametroCusto(String id, PCusto pCusto) {
        try {
            UUID pCustoId = UUID.fromString(id);
            return pCustoDao.updateParametroCusto(pCustoId, pCusto);
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException("Formato de id invalido", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiRequestException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}