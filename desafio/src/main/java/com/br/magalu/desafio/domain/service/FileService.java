package com.br.magalu.desafio.domain.service;

import com.br.magalu.desafio.api.exceptions.GeneralException;
import com.br.magalu.desafio.domain.dto.MerchantFileDTO;
import com.br.magalu.desafio.domain.dto.PurchaseFileDTO;
import com.br.magalu.desafio.domain.util.ResponseSuccess;
import com.br.magalu.desafio.domain.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final PurchaseService purchaseService;
    private final ResponseUtil response;

    public ResponseSuccess upload(MultipartFile file) {
        List<PurchaseFileDTO> purchases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split("\t");
                MerchantFileDTO merchant = new MerchantFileDTO(data[4], data[5]);
                PurchaseFileDTO purchase = new PurchaseFileDTO(
                        data[0],
                        data[1],
                        new BigDecimal(data[2].replace("R$", "").trim()),
                        Integer.parseInt(data[3]),
                        merchant
                );
                purchases.add(purchase);
            }
            purchaseService.save(purchases);
            return response.buildResponse("file.uploaded");
        } catch (Exception e) {
            log.error("Error reading file", e.getMessage());
            throw new GeneralException(e.getMessage());
        }
    }
}
