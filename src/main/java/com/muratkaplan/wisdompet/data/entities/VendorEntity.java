package com.muratkaplan.wisdompet.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "VENDORS")
public class VendorEntity {
    @Id
    @Column(name = "VENDOR_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name="CONTACT")
    private String contact;
    @Column(name="PHONE")
    private String phone;
    @Column(name="EMAIL")
    private String email;
    @Column(name="ADDRESS")
    private String address;

}
