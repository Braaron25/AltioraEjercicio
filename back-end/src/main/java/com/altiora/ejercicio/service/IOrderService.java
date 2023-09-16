package com.altiora.ejercicio.service;

import com.altiora.ejercicio.dto.OrderRQ;
import com.altiora.ejercicio.dto.UpdateOrderRQ;
import com.altiora.ejercicio.model.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getAllOrders();

    Order findByCode(String code);

    String addNewOrder(OrderRQ orderRQ);

    void updateOrder(UpdateOrderRQ updateOrderRQ);

    void deleteOrder(String cod_order);

}
