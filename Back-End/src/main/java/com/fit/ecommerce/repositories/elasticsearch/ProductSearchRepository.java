package com.fit.ecommerce.repositories.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.fit.ecommerce.entities.elasticsearch.ProductDocument;

import java.util.List;

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, String> {

}

