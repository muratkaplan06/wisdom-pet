package com.muratkaplan.wisdompet.web.rest;

import com.muratkaplan.wisdompet.services.VendorService;
import com.muratkaplan.wisdompet.web.errors.BadRequestException;
import com.muratkaplan.wisdompet.web.models.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendors")
public class VendorController {
    private final VendorService vendorService;

    @GetMapping
    List<Vendor> getAll(){
        return vendorService.getAllVendors();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor create(@RequestBody Vendor vendor){
        return vendorService.createOrUpdateVendor(vendor);
    }
    @GetMapping("/{id}")
    public Vendor getVendor(@PathVariable("id") Long id){
        return vendorService.getVendorById(id);
    }
    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable("id") Long id,@RequestBody Vendor vendor){
        if(!id.equals(vendor.getId())){
            throw new BadRequestException("Vendor id does not match");
        }
        vendor.setId(id);
        return vendorService.createOrUpdateVendor(vendor);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVendor(@PathVariable("id") Long id){
        vendorService.deleteVendorById(id);
    }
}
