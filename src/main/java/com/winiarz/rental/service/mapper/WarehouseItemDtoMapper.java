package com.winiarz.rental.service.mapper;

import com.winiarz.rental.model.WarehouseItem;
import com.winiarz.rental.model.dto.WarehouseItemDto;
import org.springframework.stereotype.Service;

@Service
public class WarehouseItemDtoMapper {

    public WarehouseItemDto toDto(WarehouseItem warehouseItem) {
        return new WarehouseItemDto(warehouseItem.getId(), warehouseItem.getName(), warehouseItem.getImageLink(), warehouseItem.getPriceForDay(), warehouseItem.getQuantity(), warehouseItem.getBail(), warehouseItem.getDescription());
    }
}
