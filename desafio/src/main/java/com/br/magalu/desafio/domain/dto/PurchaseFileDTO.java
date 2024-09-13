package com.br.magalu.desafio.domain.dto;

import java.math.BigDecimal;

public record PurchaseFileDTO(
        String purchaserName,
        String itemDescription,
        BigDecimal itemPrice,
        int purchaseCount,
        MerchantFileDTO merchant
) {
}
