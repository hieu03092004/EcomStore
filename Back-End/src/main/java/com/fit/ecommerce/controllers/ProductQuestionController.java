package com.fit.ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fit.ecommerce.dtos.request.productQuestion.ProductQuestionAddRequest;
import com.fit.ecommerce.dtos.request.productQuestion.ProductQuestionAnswerAddRequest;
import com.fit.ecommerce.dtos.response.base.PageResponse;
import com.fit.ecommerce.dtos.response.base.ResponseSuccess;
import com.fit.ecommerce.dtos.response.productQuestion.ProductQuestionResponse;
import com.fit.ecommerce.services.ProductQuestionService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/product-questions")
@RequiredArgsConstructor
public class ProductQuestionController {

    private final ProductQuestionService productQuestionService;

    @GetMapping("/{slug}")
    public ResponseEntity<ResponseSuccess<PageResponse<ProductQuestionResponse>>> getProductQuestionsByProductSlug(
            @PathVariable("slug") String slug,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "5", required = false) int size
    ) {
        return ResponseEntity.ok(new ResponseSuccess<>(
                OK,
                "Get product questions by slug success",
                productQuestionService.getProductQuestionsByProductSlug(slug,page, size)
        ));
    }


    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseSuccess<ProductQuestionResponse>> createProductQuestion(
            @Valid @RequestBody ProductQuestionAddRequest request) {

        return ResponseEntity.ok(new ResponseSuccess<>(
                CREATED,
                "Create product question success",
                productQuestionService.createProductQuestion(request)
        ));
    }

    @PostMapping("/answer")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseSuccess<ProductQuestionResponse>> createProductQuestionAnswer(
            @Valid @RequestBody ProductQuestionAnswerAddRequest request) {

        return ResponseEntity.ok(new ResponseSuccess<>(
                CREATED,
                "Create product question success",
                productQuestionService.createProductQuestionAnswer(request)
        ));
    }
}
