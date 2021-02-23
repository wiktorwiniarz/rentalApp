package com.winiarz.rental.service;

import com.winiarz.rental.model.WarehouseItem;
import com.winiarz.rental.model.dto.WarehouseItemDto;
import com.winiarz.rental.repository.WarehouseItemRepository;
import com.winiarz.rental.service.mapper.WarehouseItemDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WarehouseItemService {

    @Autowired
    private WarehouseItemRepository warehouseItemRepository;

    @Autowired
    private WarehouseItemDtoMapper warehouseItemDtoMapper;

    public List<WarehouseItem> findAll() {
        return warehouseItemRepository.findAll();
    }

    public List<WarehouseItem> findAllByActiveIsTrue() {
        return warehouseItemRepository.findAllByActiveIsTrue();
    }

    public List<WarehouseItem> findAllByActiveIsTrueAndNameLike(String itemName) {
        String like = "%" + itemName + "%";
        return warehouseItemRepository.findAllByActiveIsTrueAndNameLike(like);
    }

    public WarehouseItem newWarehouseItem(WarehouseItemDto dto) {
        String name = dto.getName();
        String imageLink = dto.getImageLink();
        double priceForDay = dto.getPriceForDay();
        int quantity = dto.getQuantity();
        double bail = dto.getBail();
        String description = dto.getDescription();

        WarehouseItem warehouseItem = new WarehouseItem(null, name, imageLink, priceForDay, quantity, bail, description, true, Collections.emptyList());
        save(warehouseItem);
        return warehouseItem;
    }

    public void save(WarehouseItem warehouseItem) {
        warehouseItemRepository.save(warehouseItem);
    }

    public WarehouseItem findById(Integer id) {
        return warehouseItemRepository.findById(id).isPresent() ? warehouseItemRepository.findById(id).get() : null;
    }

    public WarehouseItem disableItem(Integer warehouseItemId) {
        WarehouseItem warehouseItem = findById(warehouseItemId);

        if (warehouseItem != null) {
            warehouseItem.setActive(false);
            save(warehouseItem);
        }
        return warehouseItem;
    }

    public WarehouseItemDto warehouseItemDto(Integer warehouseItemId) {
        WarehouseItem warehouseItem = findById(warehouseItemId);
        return warehouseItemDtoMapper.toDto(warehouseItem);
    }

    public WarehouseItem editWarehouseItem(WarehouseItemDto dto) {
        WarehouseItem warehouseItem = findById(dto.getId());

        if (warehouseItem != null) {
            warehouseItem.setName(dto.getName());
            warehouseItem.setImageLink(dto.getImageLink());
            warehouseItem.setPriceForDay(dto.getPriceForDay());
            warehouseItem.setQuantity(dto.getQuantity());
            warehouseItem.setBail(dto.getBail());
            warehouseItem.setDescription(dto.getDescription());
            save(warehouseItem);
        }
        return warehouseItem;
    }
}
