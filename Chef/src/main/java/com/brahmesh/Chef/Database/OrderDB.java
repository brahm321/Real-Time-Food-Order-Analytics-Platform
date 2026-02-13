package com.brahmesh.Chef.Database;

import com.brahmesh.Chef.DTO.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDB extends JpaRepository<OrderEvent,Integer> {

}
