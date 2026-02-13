package com.brahmesh.Consumer.Repo;

import com.brahmesh.Consumer.DTO.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

}
