package com.muratkaplan.wisdompet.data.repositories;

import com.muratkaplan.wisdompet.data.entities.ProductEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> { }
