package com.br.magalu.desafio.api.controller;


import com.br.magalu.desafio.api.FileController;
import com.br.magalu.desafio.domain.service.FileService;
import com.br.magalu.desafio.domain.util.ResponseSuccess;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/v1/files")
@Tag(name = "File", description = "API responsible for uploading files")
@RequiredArgsConstructor
public class FileControllerImpl implements FileController {

    private final FileService service;

    @Override
    public ResponseEntity<ResponseSuccess> uploadProduto(MultipartFile file, RedirectAttributes redirectAttributes) {
        ResponseSuccess message = service.upload(file);
        redirectAttributes.addFlashAttribute("message", message.mensagem());
        return ResponseEntity.ok(message);
    }
}
