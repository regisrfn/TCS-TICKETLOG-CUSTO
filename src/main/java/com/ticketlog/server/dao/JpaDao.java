package com.ticketlog.server.dao;

import java.util.UUID;

import com.ticketlog.server.model.PCusto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDao extends JpaRepository<PCusto, UUID> {
}