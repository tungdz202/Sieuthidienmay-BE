package com.sapo.mock.techshop.controller;

import com.sapo.mock.techshop.dto.request.ProductRequestDTO;
import com.sapo.mock.techshop.dto.response.GeneralResponse;
import com.sapo.mock.techshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/dashboard/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public GeneralResponse<?> getAll(){
        return GeneralResponse.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public GeneralResponse<?> getById(@PathVariable("id") Integer id){
        return GeneralResponse.ok(productService.getById(id));
    }

    @PostMapping()
    public GeneralResponse<?> create(@RequestBody ProductRequestDTO productRequestDTO){
        return GeneralResponse.ok(productService.create(productRequestDTO));
    }

    @PostMapping("/{id}")
    public GeneralResponse<?> updateById(@PathVariable("id") Integer id, @RequestBody ProductRequestDTO productRequestDTO){
        return GeneralResponse.ok(productService.updateById(id,productRequestDTO));
    }

    @DeleteMapping("/{id}")
    public GeneralResponse<?> deleteById(@PathVariable("id") Integer id){
        productService.deleteById(id);
        return GeneralResponse.ok(null);
    }
}
