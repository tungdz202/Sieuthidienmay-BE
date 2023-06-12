package com.sapo.mock.techshop.service;

import com.sapo.mock.techshop.dto.request.ProductRequestDTO;
import com.sapo.mock.techshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Integer id);
    Product create(ProductRequestDTO productDTO);
    Product updateById(Integer id, ProductRequestDTO productDTO);
    void deleteById(Integer id);
}
