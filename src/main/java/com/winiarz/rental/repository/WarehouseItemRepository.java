package com.winiarz.rental.repository;

import com.winiarz.rental.model.WarehouseItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseItemRepository extends CrudRepository<WarehouseItem, Integer> {

    List<WarehouseItem> findAll();

    List<WarehouseItem> findAllByActiveIsTrue();

    List<WarehouseItem> findAllByActiveIsTrueAndNameLike(String name);
}
