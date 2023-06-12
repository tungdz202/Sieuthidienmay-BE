package com.sapo.mock.techshop.service.impl;

import com.sapo.mock.techshop.common.exception.BusinessException;
import com.sapo.mock.techshop.common.utils.DTOValidator;
import com.sapo.mock.techshop.dto.response.CollectionDTO;
import com.sapo.mock.techshop.entity.Collection;
import com.sapo.mock.techshop.mapper.CollectionMapper;
import com.sapo.mock.techshop.repository.CategoryRepo;
import com.sapo.mock.techshop.repository.CollectionRepo;
import com.sapo.mock.techshop.repository.ManufacturerRepo;
import com.sapo.mock.techshop.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.sapo.mock.techshop.common.constant.HttpStatusConstant.CATEGORY_NOT_FOUND_MESSAGE;
import static com.sapo.mock.techshop.common.constant.HttpStatusConstant.NOT_FOUND_CODE;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    CollectionRepo collectionRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ManufacturerRepo manufacturerRepo;

    @Autowired
    DTOValidator dtoValidator;

    @Override
    public List<Collection> getAll() {
        return collectionRepo.findAll();
    }

    @Override
    public Collection getById(Integer id) {
        return collectionRepo.findById(id)
                .orElseThrow(() -> new BusinessException(NOT_FOUND_CODE,CATEGORY_NOT_FOUND_MESSAGE));
    }

    @Override
    public Collection create(CollectionDTO collectionDTO) {
        dtoValidator.validate(collectionDTO);
        Collection collection = collectionMapper.createEntity(collectionDTO);
        collection.setCategory(categoryRepo.getById(collectionDTO.getCategory_id()));
        collection.setManufacturer(manufacturerRepo.getById(collectionDTO.getManufacturer_id()));
        return collectionRepo.save(collection);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Collection updateById(Integer id, CollectionDTO collectionDTO) {
        dtoValidator.validate(collectionDTO);
        Collection collection = getById(id);
        collectionMapper.updateEntity(collection,collectionDTO);
        return collection;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(Integer id) {
        getById(id);
        collectionRepo.deleteById(id);
    }
}