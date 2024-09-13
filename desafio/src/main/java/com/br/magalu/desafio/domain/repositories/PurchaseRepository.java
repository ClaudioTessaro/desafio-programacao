package com.br.magalu.desafio.domain.repositories;

import com.br.magalu.desafio.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}