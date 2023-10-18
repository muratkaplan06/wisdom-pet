package com.muratkaplan.wisdompet.data.repositories;

import com.muratkaplan.wisdompet.data.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<ProductEntity, Long> { }
