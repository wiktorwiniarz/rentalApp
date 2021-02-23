package com.winiarz.rental.service;

import com.winiarz.rental.model.*;
import com.winiarz.rental.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private WarehouseItemService warehouseItemService;

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private EmailService emailService;

    public ShoppingCart findByUserAndStatus(User user, ShoppingCartStatus status) {
        return shoppingCartRepository.findByUserAndStatus(user, status).isPresent() ? shoppingCartRepository.findByUserAndStatus(user, status).get() : null;
    }

    public List<ShoppingCart> findAllByUserAndStatusIsNotPreparation(HttpServletRequest request) {
        User user = userService.findBySession(request);
        return shoppingCartRepository.findAllByUserAndStatusIsNot(user, ShoppingCartStatus.PREPARATION);
    }

    public List<ShoppingCart> findAllByStatusIsNotPreparation() {
        return shoppingCartRepository.findAllByStatusIsNot(ShoppingCartStatus.PREPARATION);
    }

    public ShoppingCart findById(Integer shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId).isPresent() ? shoppingCartRepository.findById(shoppingCartId).get() : null;
    }

    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart findInPreparationOrCreate(HttpServletRequest request) {
        User user = userService.findBySession(request);
        ShoppingCart shoppingCart;

        if (user != null) {
            shoppingCart = findByUserAndStatus(user, ShoppingCartStatus.PREPARATION);
            if (shoppingCart != null) return shoppingCart;

            shoppingCart = new ShoppingCart(null, 0, null, ShoppingCartStatus.PREPARATION, user, Collections.emptyList());
            save(shoppingCart);
            return shoppingCart;
        }
        return null;
    }

    public void save(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    public void delete(ShoppingCart shoppingCart) {
        shoppingCartRepository.delete(shoppingCart);
    }

    public ShoppingCart newUserOrder(HttpServletRequest req, Integer warehouseItemId, Integer quantity, String fromDate, String toDate) {
        ShoppingCart shoppingCart = findInPreparationOrCreate(req);
        WarehouseItem warehouseItem = warehouseItemService.findById(warehouseItemId);

        if (shoppingCart != null && warehouseItem != null) {
            UserOrder userOrder = userOrderService.newUserOrder(shoppingCart, warehouseItem, quantity, fromDate, toDate);
            if (userOrder != null) {
                shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + userOrder.getTotalPrice());
                save(shoppingCart);
            }
        }
        return shoppingCart;
    }

    public ShoppingCart deleteUserOrder(Integer shoppingCartId, Integer userOrderId) {
        ShoppingCart shoppingCart = findById(shoppingCartId);
        if (shoppingCart != null) {
            UserOrder userOrder = userOrderService.deleteUserOrder(userOrderId);
            if (userOrder != null) {
                shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - userOrder.getTotalPrice());
                save(shoppingCart);
            }
        }
        return shoppingCart;
    }


    public List<UserOrder> changeShoppingCartStatusToWaiting(Integer shoppingCartId) {
        ShoppingCart shoppingCart = findById(shoppingCartId);
        List<UserOrder> cannotBeBooked = new ArrayList<>();

        List<UserOrder> accepted = new ArrayList<>();
        for (UserOrder order : shoppingCart.getUserOrderList()) {
            if (order.getQuantity() > order.getWarehouseItem().getQuantity()) {
                cannotBeBooked.add(order);
                continue;
            }

            List<UserOrder> userOrderList = userOrderService.findAllByWarehouseItemAndStatusIsNotAndStatusIsNotAndStatusIsNot(order.getWarehouseItem(),
                    UserOrderStatus.PRZYGOTOWANIE, UserOrderStatus.ANULOWANE, UserOrderStatus.ZWROCONE);
            for (UserOrder element : accepted) {
                if (element.getWarehouseItem().getId().equals(order.getWarehouseItem().getId()))
                    userOrderList.add(element);
            }

            int quantity = order.getQuantity();
            LocalDate from1 = order.getFromDate();
            LocalDate to1 = order.getToDate();
            boolean flag = true;

            for (UserOrder order2 : userOrderList) {
                LocalDate from2 = order2.getFromDate();
                LocalDate to2 = order2.getToDate();

                if (from1.equals(from2) || from1.isAfter(from2) && (from1.isBefore(to2) || from1.equals(to2)) ||
                        to1.equals(from2) || to1.isAfter(from2) && (to1.isBefore(to2) || to1.equals(to2)) ||
                        from1.isBefore(from2) && to1.isAfter(to2) ||
                        from1.equals(from2) && to1.equals(to2)
                ) {
                    quantity += order2.getQuantity();
                    if (quantity > order2.getWarehouseItem().getQuantity()) {
                        cannotBeBooked.add(order);
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                accepted.add(order);
            }
        }

        if (cannotBeBooked.isEmpty()) {
            shoppingCart.setStatus(ShoppingCartStatus.READY);
            shoppingCart.setBuyDate(LocalDate.now());
            save(shoppingCart);

            for (UserOrder element : shoppingCart.getUserOrderList()) {
                userOrderService.changeStatus(element, UserOrderStatus.OCZEKUJE);
            }

            emailService.shoppingCartConfirmation(shoppingCart.getUser().getEmail());
        }
        return cannotBeBooked;
    }

    public List<UserOrder> adminConfirmShoppingCart(Integer shoppingCartId) {
        ShoppingCart shoppingCart = findById(shoppingCartId);
        List<UserOrder> cannotBeBooked = new ArrayList<>();

        List<UserOrder> accepted = new ArrayList<>();
        for (UserOrder order : shoppingCart.getUserOrderList()) {
            if (order.getQuantity() > order.getWarehouseItem().getQuantity()) {
                cannotBeBooked.add(order);
                continue;
            }

            List<UserOrder> userOrderList = userOrderService.findAllByWarehouseItemAndStatusIsNotAndStatusIsNotAndStatusIsNot(order.getWarehouseItem(), UserOrderStatus.PRZYGOTOWANIE, UserOrderStatus.ANULOWANE, UserOrderStatus.ZWROCONE);
            for (UserOrder element : accepted) {
                if (element.getWarehouseItem().getId().equals(order.getWarehouseItem().getId()))
                    userOrderList.add(element);
            }

            int quantity = order.getQuantity();
            LocalDate from1 = order.getFromDate();
            LocalDate to1 = order.getToDate();
            boolean flag = true;

            for (UserOrder order2 : userOrderList) {
                LocalDate from2 = order2.getFromDate();
                LocalDate to2 = order2.getToDate();

                if (from1.equals(from2) || from1.isAfter(from2) && (from1.isBefore(to2) || from1.equals(to2)) ||
                        to1.equals(from2) || to1.isAfter(from2) && (to1.isBefore(to2) || to1.equals(to2)) ||
                        from1.isBefore(from2) && to1.isAfter(to2) ||
                        from1.equals(from2) && to1.equals(to2)
                ) {
                    quantity += order2.getQuantity();
                    if (quantity > order2.getWarehouseItem().getQuantity()) {
                        cannotBeBooked.add(order);
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                accepted.add(order);
            }
        }

        if (cannotBeBooked.isEmpty()) {
            shoppingCart.setBuyDate(LocalDate.now());
            save(shoppingCart);
        }
        return cannotBeBooked;
    }

    public ShoppingCart adminSetUserToShoppingCart(Integer shoppingCartId, String firstName, String lastName, String phoneNumber, String email) {
        ShoppingCart shoppingCart = findById(shoppingCartId);
        User user = userService.findByEmail(email);
        if (user == null) user = userService.newUser(firstName, lastName, phoneNumber, email, null);

        if (shoppingCart != null && user != null) {
            shoppingCart.setUser(user);
            shoppingCart.setStatus(ShoppingCartStatus.READY);
            save(shoppingCart);

            for (UserOrder element : shoppingCart.getUserOrderList()) {
                userOrderService.changeStatus(element, UserOrderStatus.OCZEKUJE);
            }

            emailService.shoppingCartConfirmation(shoppingCart.getUser().getEmail());
        }
        return shoppingCart;
    }

    public ShoppingCart changeStatus(Integer shoppingCartId, ShoppingCartStatus shoppingCartStatus) {
        ShoppingCart shoppingCart = findById(shoppingCartId);
        if (shoppingCart != null) {
            shoppingCart.setStatus(shoppingCartStatus);
            save(shoppingCart);
        }
        return shoppingCart;
    }

    public List<ShoppingCart> findAllByStatus(ShoppingCartStatus shoppingCartStatus) {
        return shoppingCartRepository.findAllByStatus(shoppingCartStatus);
    }
}
