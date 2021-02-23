package com.winiarz.rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseItemDto {

    private Integer id;
    private String name;
    private String imageLink;
    private double priceForDay;
    private int quantity;
    private double bail;
    private String description;
}
