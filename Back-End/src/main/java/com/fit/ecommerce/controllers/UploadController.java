package com.fit.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fit.ecommerce.dtos.request.upload.UploadRequest;
import com.fit.ecommerce.dtos.response.base.ResponseSuccess;
import com.fit.ecommerce.services.UploadService;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/uploads")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseSuccess<List<String>>> upload(@ModelAttribute UploadRequest uploadRequest) {
        return ResponseEntity.ok(new ResponseSuccess<>(HttpStatus.OK,
                "Upload image success", uploadService.upload(uploadRequest)));
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseSuccess<Void>> deleteFile(@RequestParam String url) {
        uploadService.deleteFile(url);
        return ResponseEntity.ok(new ResponseSuccess<>(HttpStatus.OK, "Xóa ảnh thành công", null));
    }
}