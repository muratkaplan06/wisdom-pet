package com.muratkaplan.wisdompet.data.repositories;

import com.muratkaplan.wisdompet.data.entities.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {
}
