package com.muratkaplan.wisdompet.services;

import com.muratkaplan.wisdompet.data.entities.VendorEntity;
import com.muratkaplan.wisdompet.data.repositories.VendorRepository;
import com.muratkaplan.wisdompet.web.errors.NotFoundException;
import com.muratkaplan.wisdompet.web.models.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorService {
    public final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    public List<Vendor> getAllVendors(){
        Iterable<VendorEntity> entities = vendorRepository.findAll();
        List<Vendor> vendors = new ArrayList<>();
        for(VendorEntity entity: entities){
            vendors.add(translateDbToWeb(entity));
        }
        return vendors;
    }
    public Vendor getVendorById(Long id){
        VendorEntity entity = vendorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendor not found"));
        return translateDbToWeb(entity);
    }
    public Vendor createOrUpdateVendor(Vendor vendor){
        VendorEntity entity = translateWebToDb(vendor);
        VendorEntity savedEntity = vendorRepository.save(entity);
        return translateDbToWeb(savedEntity);
    }
    public void deleteVendorById(Long id){
        this.vendorRepository.deleteById(id);
    }

    public VendorEntity translateWebToDb(Vendor vendor){
        VendorEntity entity = new VendorEntity();
        entity.setId(vendor.getVendorId());
        entity.setName(vendor.getName());
        entity.setContact(vendor.getContact());
        entity.setPhone(vendor.getPhone());
        entity.setEmail(vendor.getEmail());
        entity.setAddress(vendor.getAddress());
        return entity;
    }
    public Vendor translateDbToWeb(VendorEntity entity){
        return new Vendor(
                entity.getId(),
                entity.getName(),
                entity.getContact(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getAddress()
        );
    }
}
