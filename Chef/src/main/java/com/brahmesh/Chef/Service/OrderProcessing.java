package com.brahmesh.Chef.Service;

import com.brahmesh.Chef.DTO.OrderEvent;
import com.brahmesh.Chef.Database.OrderDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@Slf4j
public class OrderProcessing {

    @Autowired
    private OrderDB orderDB;

    private final ExecutorService chefPool = Executors.newFixedThreadPool(10);

    public void process(OrderEvent order) {
        chefPool.submit(() -> {
            try {
                prepare(order);
                delivery(order);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    private void prepare(OrderEvent order) throws InterruptedException {
        order.setStatus("Preparing");
        orderDB.save(order); // Updated DB to 'Preparing'

        log.info("Preparing order {}", order.getId());
        Thread.sleep(order.getCookingTime() * 100L);
        log.info("Order {} ready for delivery.", order.getId());
    }

    private void delivery(OrderEvent order) throws InterruptedException {
        int dTime = order.getCookingTime() * 200;
        order.setDeliveryTime(dTime);
        order.setStatus("Delivered");

        log.info("Delivering order {}", order.getId());
        Thread.sleep(dTime);

        orderDB.save(order); // Final Save
        log.info("Order {} Delivered", order.getId());
    }
}