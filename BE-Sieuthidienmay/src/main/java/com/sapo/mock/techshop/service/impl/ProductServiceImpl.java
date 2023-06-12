package com.sapo.mock.techshop.service.impl;

import com.sapo.mock.techshop.common.exception.BusinessException;
import com.sapo.mock.techshop.common.utils.DTOValidator;
import com.sapo.mock.techshop.dto.request.ProductRequestDTO;
import com.sapo.mock.techshop.entity.Order;
import com.sapo.mock.techshop.entity.Product;
import com.sapo.mock.techshop.entity.Product;
import com.sapo.mock.techshop.entity.Product;
import com.sapo.mock.techshop.mapper.ProductMapper;
import com.sapo.mock.techshop.repository.CollectionRepo;
import com.sapo.mock.techshop.repository.ProductRepo;
import com.sapo.mock.techshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.sapo.mock.techshop.common.constant.HttpStatusConstant.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    DTOValidator dtoValidator;
    @Autowired
    CollectionRepo collectionRepo;

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product getById(Integer id) {
        Product product = productRepo.findById(id)
                .orElseThrow(( () -> new BusinessException(NOT_FOUND_CODE, PRODUCT_NOT_FOUND_MESSAGE)));
        return product;
    }

    @Override
    public Product create(ProductRequestDTO productRequestDTO) {
        dtoValidator.validate(productRequestDTO);
        Product product = productMapper.createEntity(productRequestDTO);
        product.setCollection(collectionRepo.getById(productRequestDTO.getCollectionId()));
        return productRepo.save(product);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Product updateById(Integer id, ProductRequestDTO productRequestDTO) {
        dtoValidator.validate(productRequestDTO);

        Product product = getById(id);

        productMapper.updateEntity(product, productRequestDTO);

        return productRepo.save(product);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(Integer id) {
        // Update status as 0
        Product product = getById(id);
        product.setStatus(0);
        productRepo.save(product);
    }
}
