package com.muratkaplan.wisdompet.services;

import com.muratkaplan.wisdompet.data.entities.ServiceEntity;
import com.muratkaplan.wisdompet.data.repositories.ServiceRepository;
import com.muratkaplan.wisdompet.web.errors.NotFoundException;
import com.muratkaplan.wisdompet.web.models.Service;

import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    public List<Service> getAllServices(){
       Iterable<ServiceEntity> entities= this.serviceRepository.findAll();
         List<Service> services = new ArrayList<>();
            for(ServiceEntity entity: entities){
                services.add(translateDbToWeb(entity));
            }
            return services;
    }
    public Service getServiceById(Long id){
        ServiceEntity entity = this.serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service not found"));
        return translateDbToWeb(entity);
    }
    public Service createOrUpdateService(Service service){
        ServiceEntity entity = translateWebToDb(service);
        ServiceEntity savedEntity = this.serviceRepository.save(entity);
        return translateDbToWeb(savedEntity);
    }
    public void deleteServiceById(Long id){
        this.serviceRepository.deleteById(id);
    }

    private ServiceEntity translateWebToDb(Service service) {
        ServiceEntity entity = new ServiceEntity();
        entity.setId(service.getServiceId());
        entity.setName(service.getName());
        entity.setPrice(service.getPrice());
        return entity;
    }
    private Service translateDbToWeb(ServiceEntity entity) {
        return new Service(
                entity.getId(),
                entity.getName(),
                entity.getPrice()
        );
    }
}
