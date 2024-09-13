package com.br.magalu.desafio.api;

import com.br.magalu.desafio.api.exceptions.ResponseError;
import com.br.magalu.desafio.domain.util.ResponseSuccess;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/v1/files")
@Tag(name = "File", description = "API responsible for uploading files")
public interface FileController {


    @PostMapping(value = "/purchase", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(
            {@ApiResponse(
                    responseCode = "200",
                    description = "Sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ResponseSuccess.class
                            )
                    )}
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ResponseError.class
                            )
                    )}
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ResponseError.class
                            )
                    )}
            )})
    ResponseEntity<ResponseSuccess> uploadProduto(@RequestParam(value = "file", required = false) MultipartFile file, RedirectAttributes redirectAttributes);

}
