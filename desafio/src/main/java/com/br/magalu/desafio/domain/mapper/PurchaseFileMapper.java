package com.br.magalu.desafio.domain.mapper;

import com.br.magalu.desafio.domain.dto.PurchaseFileDTO;
import com.br.magalu.desafio.domain.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PurchaseFileMapper {


    @Mapping(target = "purchaser.name", source = "purchaserName")
    @Mapping(target = "itemDescription", source = "itemDescription")
    @Mapping(target = "itemPrice", source = "itemPrice")
    @Mapping(target = "purchaseCount", source = "purchaseCount")
    @Mapping(target = "merchant.name", source = "merchant.name")
    @Mapping(target = "merchant.address", source = "merchant.address")
    Purchase toEntity(PurchaseFileDTO purchaseFileDTO);

    List<Purchase> toEntity(List<PurchaseFileDTO> purchaseFileDTO);
}
