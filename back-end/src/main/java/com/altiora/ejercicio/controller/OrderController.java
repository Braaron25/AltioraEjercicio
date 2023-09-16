package com.altiora.ejercicio.controller;

import com.altiora.ejercicio.dto.OrderRQ;
import com.altiora.ejercicio.dto.UpdateOrderRQ;
import com.altiora.ejercicio.exception.DataException;
import com.altiora.ejercicio.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping(path = "/getAllOrders")
    public ResponseEntity getAllOrders(){
        try {
            return ResponseEntity.ok(this.orderService.getAllOrders());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/findByCode/{code}")
    public ResponseEntity findByCode(@PathVariable String code){
        try {
            return ResponseEntity.ok(this.orderService.findByCode(code));
        } catch (DataException d){
            log.error(d.getMessage());
            return ResponseEntity.badRequest().body(d.getMessage());
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/createOrder")
    public ResponseEntity addNewOrder(@RequestBody OrderRQ orderRQ){
        try {
            this.orderService.addNewOrder(orderRQ);
            return ResponseEntity.ok().build();
        } catch (DataException d){
            log.error(d.getMessage());
            return ResponseEntity.badRequest().body(d.getMessage());
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(path = "/updateOrder")
    public ResponseEntity updateOrder(@RequestBody UpdateOrderRQ updateOrderRQ){
        try {
            this.orderService.updateOrder(updateOrderRQ);
            return  ResponseEntity.ok().build();
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/deleteOrder/{code}")
    public ResponseEntity deleteOrder(@PathVariable String code){
        try {
            this.orderService.deleteOrder(code);
            return ResponseEntity.ok().build();
        } catch (DataException d){
            log.error(d.getMessage());
            return ResponseEntity.badRequest().body(d.getMessage());
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
