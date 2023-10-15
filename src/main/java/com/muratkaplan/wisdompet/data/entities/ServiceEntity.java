package com.muratkaplan.wisdompet.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@ToString
@Table(name = "SERVICES")
public class ServiceEntity {
    @Id
    @Column(name = "SERVICE_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name="PRICE")
    private BigDecimal price;

}
