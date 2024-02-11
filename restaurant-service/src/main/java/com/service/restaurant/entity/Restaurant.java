package com.service.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "tb_restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RestaurantID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "StartTime")
    private String startTime;

    @Column(name = "CloseTime")
    private String closeTime;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive = true;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FoodItem> foodItems = new ArrayList<FoodItem>();

}
