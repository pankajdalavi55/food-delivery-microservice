package com.service.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryRequestDTO {

    private Long orderId;
    private Long customerId;
    private Long restaurantId;
    private String pickupAddress;
    private String deliveryAddress;

}
