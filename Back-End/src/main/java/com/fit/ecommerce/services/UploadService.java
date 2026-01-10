package com.fit.ecommerce.services;

import java.util.List;

import com.fit.ecommerce.dtos.request.upload.UploadRequest;


public interface UploadService {
    List<String> upload(UploadRequest uploadRequest);
    void deleteFile(String url);
}
