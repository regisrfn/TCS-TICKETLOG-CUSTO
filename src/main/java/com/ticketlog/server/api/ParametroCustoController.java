package com.ticketlog.server.api;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.ticketlog.server.model.PCusto;
import com.ticketlog.server.service.ParametroCustoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/custo")
@CrossOrigin
public class ParametroCustoController {

    private ParametroCustoService pCustoService;

    @Autowired
    public ParametroCustoController(ParametroCustoService pCustoService) {
        this.pCustoService = pCustoService;
    }

    @PostMapping("save")
    public ResponseEntity<Object> saveParametroCusto(@Valid @RequestBody PCusto pCusto) {
        PCusto pCustoSaved = pCustoService.saveParametroCusto(pCusto);
        return new ResponseEntity<>(pCustoSaved, HttpStatus.OK);
    }

    @GetMapping("get")
    public List<PCusto> getAllParametroCustos() {
        return pCustoService.getAllParametroCustos();
    }

    @GetMapping("get/{id}")
    public PCusto getParametroCustoById(@PathVariable String id) {
        return pCustoService.getParametroCustoById(id);
    }

    @DeleteMapping("delete/{id}")
    public Map<String, String> deleteParametroCustoById(@PathVariable String id) {
        pCustoService.deleteParametroCustoById(id);
        return Map.of("message", "successfully operation");
    }

    @PutMapping("update/{id}")
    public PCusto updateParametroCusto(@PathVariable String id, @Valid @RequestBody PCusto pCusto) {
        return pCustoService.updateParametroCusto(id, pCusto);
    }
}