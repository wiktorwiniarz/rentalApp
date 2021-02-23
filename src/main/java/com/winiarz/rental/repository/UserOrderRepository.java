package com.winiarz.rental.repository;

import com.winiarz.rental.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserOrderRepository extends CrudRepository<UserOrder, Integer> {

    List<UserOrder> findAllByWarehouseItemAndShoppingCart_StatusIsNotOrderByFromDateAsc(WarehouseItem warehouseItem, ShoppingCartStatus status);

    Optional<UserOrder> findByShoppingCartAndWarehouseItem(ShoppingCart shoppingCart, WarehouseItem warehouseItem);

    List<UserOrder> findAllByStatus(UserOrderStatus status);

    List<UserOrder> findAllByStatusOrStatus(UserOrderStatus status1, UserOrderStatus status2);

    List<UserOrder> findAllByShoppingCart_UserAndStatusIsNot(User user, UserOrderStatus userOrderStatus);

    List<UserOrder> findAllByStatusOrderByFromDateAsc(UserOrderStatus status);

    List<UserOrder> findAllByWarehouseItemAndStatusIsNotAndStatusIsNotAndStatusIsNot(WarehouseItem warehouseItem, UserOrderStatus status1, UserOrderStatus status2, UserOrderStatus status3);

}