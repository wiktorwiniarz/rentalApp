package com.winiarz.rental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double totalPrice;
    private double rentalPrice;
    private double bailPrice;
    private int quantity;
    private LocalDate fromDate;
    private LocalDate toDate;
    private UserOrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;

    @ManyToOne(fetch = FetchType.LAZY)
    private WarehouseItem warehouseItem;
}