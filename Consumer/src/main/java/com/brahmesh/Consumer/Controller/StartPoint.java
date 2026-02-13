package com.brahmesh.Consumer.Controller;

import com.brahmesh.Consumer.DTO.Order;
import com.brahmesh.Consumer.Service.ValidOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StartPoint {

    @Autowired
    private ValidOrder validOrder;

    @PostMapping("/api/orders")
    public ResponseEntity<?> createOrder(@RequestBody Order order)
    {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            System.out.println(order.getId() + " Discarded ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Order " + order.getId() +" must have at least one item.");
        }

        log.info("Order Check Successfully");
        validOrder.initialCheck(order);
        return ResponseEntity.ok(order);
    }
}
