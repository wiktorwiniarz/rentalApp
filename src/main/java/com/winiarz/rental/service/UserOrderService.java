package com.winiarz.rental.service;

import com.winiarz.rental.model.*;
import com.winiarz.rental.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

@Service
public class UserOrderService {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public List<UserOrder> findAllByStatus(UserOrderStatus status) {
        return userOrderRepository.findAllByStatus(status);
    }

    public List<UserOrder> findAllByStatusOrderByFromDateAsc(UserOrderStatus status) {
        return userOrderRepository.findAllByStatusOrderByFromDateAsc(status);
    }

    public List<UserOrder> findAllByStatusOrStatus(UserOrderStatus status1, UserOrderStatus status2) {
        return userOrderRepository.findAllByStatusOrStatus(status1, status2);
    }

    public List<UserOrder> findAllByUserSession(HttpServletRequest req) {
        User user = userService.findBySession(req);

        if (user != null) {
            return userOrderRepository.findAllByShoppingCart_UserAndStatusIsNot(user, UserOrderStatus.PRZYGOTOWANIE);
        }
        return Collections.emptyList();
    }

    public UserOrder newUserOrder(ShoppingCart shoppingCart, WarehouseItem warehouseItem, Integer quantity, String fromDate, String toDate) {
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);
        UserOrder userOrder = null;

        if (from.isBefore(to) || from.isEqual(to)) {
            int days = Period.between(from, to).getDays() + 1;
            double rentalPrice = (warehouseItem.getPriceForDay() * quantity) * days;
            double bailPrice = warehouseItem.getBail() * quantity;
            double totalPrice = rentalPrice + bailPrice;

            userOrder = new UserOrder(null, totalPrice, rentalPrice, bailPrice, quantity, from, to, UserOrderStatus.PRZYGOTOWANIE, shoppingCart, warehouseItem);
            save(userOrder);
        }
        return userOrder;
    }

    public UserOrder findByShoppingCartAndWarehouseItem(ShoppingCart shoppingCart, WarehouseItem warehouseItem) {
        return userOrderRepository.findByShoppingCartAndWarehouseItem(shoppingCart, warehouseItem).isPresent() ? userOrderRepository.findByShoppingCartAndWarehouseItem(shoppingCart, warehouseItem).get() : null;
    }

    public void save(UserOrder userOrder) {
        userOrderRepository.save(userOrder);
    }

    public void delete(UserOrder userOrder) {
        userOrderRepository.delete(userOrder);
    }

    public UserOrder findById(Integer userOrderId) {
        return userOrderRepository.findById(userOrderId).isPresent() ? userOrderRepository.findById(userOrderId).get() : null;
    }

    public UserOrder deleteUserOrder(Integer userOrderId) {
        UserOrder userOrder = findById(userOrderId);
        if (userOrder != null) {
            delete(userOrder);
        }
        return userOrder;
    }

    public List<UserOrder> findAllByWarehouseItem(WarehouseItem warehouseItem) {
        return userOrderRepository.findAllByWarehouseItemAndShoppingCart_StatusIsNotOrderByFromDateAsc(warehouseItem, ShoppingCartStatus.PREPARATION);
    }

    public UserOrder changeStatus(Integer userOrderId, UserOrderStatus userOrderStatus) {
        UserOrder userOrder = findById(userOrderId);

        if (userOrder != null) {
            userOrder.setStatus(userOrderStatus);
            save(userOrder);

            if (userOrderStatus.equals(UserOrderStatus.GOTOWE)) {
                emailService.userOrderConfirmation(userOrder.getShoppingCart().getUser().getEmail());
            } else if (userOrderStatus.equals(UserOrderStatus.ANULOWANE)) {
                emailService.shoppingCartDeleteConfirmation(userOrder.getShoppingCart().getUser().getEmail());
            }
        }
        return userOrder;
    }

    public UserOrder changeStatus(UserOrder userOrder, UserOrderStatus userOrderStatus) {
        if (userOrder != null) {
            userOrder.setStatus(userOrderStatus);
            save(userOrder);
        }
        return userOrder;
    }

    public List<UserOrder> findAllByWarehouseItemAndStatusIsNotAndStatusIsNotAndStatusIsNot(WarehouseItem warehouseItem, UserOrderStatus status1, UserOrderStatus status2, UserOrderStatus status3) {
        return userOrderRepository.findAllByWarehouseItemAndStatusIsNotAndStatusIsNotAndStatusIsNot(warehouseItem, status1, status2, status3);
    }

    public int borrowedQuantity(WarehouseItem warehouseItem) {
        List<UserOrder> userOrderList = userOrderRepository.findAllByWarehouseItemAndStatusIsNotAndStatusIsNotAndStatusIsNot(warehouseItem, UserOrderStatus.PRZYGOTOWANIE, UserOrderStatus.ANULOWANE, UserOrderStatus.ZWROCONE);
        LocalDate now = LocalDate.now();

        int quantity = 0;
        for (UserOrder order : userOrderList) {
            LocalDate from = order.getFromDate();
            LocalDate to = order.getToDate();

            if (now.isEqual(from) ||
                    now.isAfter(from) && now.isBefore(to) ||
                    now.isEqual(to)
            ) {
                quantity += order.getQuantity();
            }
        }
        return quantity;
    }
}
