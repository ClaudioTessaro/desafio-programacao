package com.br.magalu.desafio.domain.service;

import com.br.magalu.desafio.domain.dto.PurchaseFileDTO;
import com.br.magalu.desafio.domain.mapper.PurchaseFileMapper;
import com.br.magalu.desafio.domain.model.Purchase;
import com.br.magalu.desafio.domain.repositories.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository repository;
    private final PurchaseFileMapper mapper;

    @Transactional
    public void save(List<PurchaseFileDTO> purchases) {
        List<Purchase> purchase = mapper.toEntity(purchases);
        repository.saveAll(purchase);
    }
}
