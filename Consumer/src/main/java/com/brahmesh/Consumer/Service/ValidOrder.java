package com.brahmesh.Consumer.Service;
import com.brahmesh.Consumer.DTO.Order;
import com.brahmesh.Consumer.Repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
@Slf4j
public class ValidOrder {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
   private KafkaTemplate<String,Order> kafkaTemplate;

    private static final String TOPIC = "topic_0";

    public void initialCheck(Order order) {

        if(order.getCookingTime() >120) {
            log.info("This order {} can't be prepared due to time constraint", order.getId());
        }
        else {
            orderRepo.save(order);
            log.info("This order {} is going for preparing to Chef Preparation", order.getId());
         //   System.out.println(order);
            addingFields(order);
        }
    }

    private void addingFields(Order order) {

        if(order.getId()%2==0) {
            order.setPlacedAt("WEB");
            log.info("Order: {} placed at WEB",order.getId());
        }
        else
        {
            order.setPlacedAt("ANDROID");
            log.info("Order: {} placed at ANDROID",order.getId());
        }
            kafkaTemplate.send(TOPIC,order);
    }


}
