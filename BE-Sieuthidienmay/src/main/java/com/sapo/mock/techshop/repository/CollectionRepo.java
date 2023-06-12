package com.sapo.mock.techshop.repository;

import com.sapo.mock.techshop.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepo extends JpaRepository<Collection, Integer> {
}