package com.brahmesh.Chef.Repositories;


import com.brahmesh.Chef.DTO.OrderEvent;
import com.brahmesh.Chef.Service.OrderProcessing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Incoming {

    @Autowired
    private OrderProcessing orderProcessing;

    @KafkaListener(topics = "topic_0", groupId = "chef-group")
    public void receivingShipment(OrderEvent order) throws InterruptedException {
        log.info("Received Order: {}", order.getId());
        log.info(" Going to Chef for processing");
        orderProcessing.process(order);
    }



}
