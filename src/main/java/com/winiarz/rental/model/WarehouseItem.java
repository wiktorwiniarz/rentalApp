package com.winiarz.rental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WarehouseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(length = 12000)
    private String imageLink;

    private double priceForDay;
    private int quantity;
    private double bail;
    private String description;
    private boolean active;

    @OneToMany(mappedBy = "warehouseItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserOrder> userOrderList;
}
