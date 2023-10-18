package com.muratkaplan.wisdompet.web.rest;

import com.muratkaplan.wisdompet.services.ServiceService;
import com.muratkaplan.wisdompet.web.errors.BadRequestException;
import com.muratkaplan.wisdompet.web.models.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping
    public List<Service> getAll(){
        return serviceService.getAllServices();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Service create(@RequestBody Service service){
        return serviceService.createOrUpdateService(service);
    }
    @GetMapping("/{id}")
    public Service getService(@PathVariable("id") Long id){
        return serviceService.getServiceById(id);
    }
    @PutMapping("/{id}")
    public Service updateService(@PathVariable("id") Long id,@RequestBody Service service){
        if(!id.equals(service.getId())){
            throw new BadRequestException("Service id does not match");
        }
        service.setId(id);
        return serviceService.createOrUpdateService(service);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable("id") Long id){
        serviceService.deleteServiceById(id);
    }
}
