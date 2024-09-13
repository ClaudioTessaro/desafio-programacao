package com.br.magalu.desafio;

import com.br.magalu.desafio.api.exceptions.GeneralException;
import com.br.magalu.desafio.domain.service.FileService;
import com.br.magalu.desafio.domain.service.PurchaseService;
import com.br.magalu.desafio.domain.util.ResponseSuccess;
import com.br.magalu.desafio.domain.util.ResponseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FilServiceTest {



    @Mock
    private PurchaseService purchaseService;

    @Mock
    private ResponseUtil responseUtil;

    @InjectMocks
    private FileService fileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadSuccess() throws Exception {
        File file = new File("src/test/resources/testfile.txt");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", inputStream);

        ResponseSuccess responseSuccess = new ResponseSuccess("file.uploaded", null);
        when(responseUtil.buildResponse("file.uploaded")).thenReturn(responseSuccess);

        ResponseSuccess result = fileService.upload(multipartFile);

        assertEquals("file.uploaded", result.mensagem());
        verify(purchaseService, times(1)).save(anyList());
    }

    @Test
    void testUploadFailure() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getInputStream()).thenThrow(new RuntimeException("File read error"));

        Exception exception = assertThrows(GeneralException.class, () -> {
            fileService.upload(file);
        });

        assertEquals("File read error", exception.getMessage());
        verify(purchaseService, never()).save(anyList());
    }
}
