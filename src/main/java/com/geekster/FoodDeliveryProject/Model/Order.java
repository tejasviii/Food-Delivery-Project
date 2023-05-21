package com.geekster.FoodDeliveryProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @JoinColumn(name = "foodId")
    @OneToOne
    private FoodItem foodId;
    @ManyToOne(fetch = FetchType.LAZY)
    private User userid;

}