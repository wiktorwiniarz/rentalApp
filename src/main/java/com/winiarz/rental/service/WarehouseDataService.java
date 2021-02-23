package com.winiarz.rental.service;

import com.winiarz.rental.model.WarehouseItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseDataService {

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private WarehouseItemService warehouseItemService;

    public List<WarehouseData> calculateWarehouseData() {
        List<WarehouseItem> availableWarehouseItems = warehouseItemService.findAllByActiveIsTrue();
        List<WarehouseData> warehouseItemDataList = new ArrayList<>();

        for (WarehouseItem element : availableWarehouseItems) {
            int borrowedQuantity = userOrderService.borrowedQuantity(element);
            int availableQuantity = element.getQuantity() - borrowedQuantity;

            WarehouseData item = new WarehouseData(element.getId(), element.getName(), element.getQuantity(), availableQuantity, borrowedQuantity, element.getPriceForDay(), element.getBail());
            warehouseItemDataList.add(item);
        }
        return warehouseItemDataList;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private class WarehouseData {
        private Integer id;
        private String name;
        private int maxQuantity;
        private int availableQuantity;
        private int borrowedQuantity;
        private double priceForDay;
        private double bail;
    }
}
