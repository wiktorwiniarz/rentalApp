package com.winiarz.rental.repository;

import com.winiarz.rental.model.ShoppingCart;
import com.winiarz.rental.model.ShoppingCartStatus;
import com.winiarz.rental.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {

    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

    List<ShoppingCart> findAll();

    List<ShoppingCart> findAllByUserAndStatusIsNot(User user, ShoppingCartStatus status);

    List<ShoppingCart> findAllByStatusIsNot(ShoppingCartStatus status);

    List<ShoppingCart> findAllByStatus(ShoppingCartStatus shoppingCartStatus);
}
