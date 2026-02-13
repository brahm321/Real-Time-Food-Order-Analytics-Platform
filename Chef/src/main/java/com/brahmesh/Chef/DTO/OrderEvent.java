package com.brahmesh.Chef.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Entity
public class OrderEvent {

    @Id
    private int id;
    private String customerName;
    private List<String> items;
    private Double totalAmount;
    private int cookingTime;
    private String status;
    private String deliveryAddress;
    private LocalDateTime orderTime;
    private String placedAt;

    private Integer deliveryTime;
}
