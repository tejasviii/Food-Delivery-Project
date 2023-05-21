package com.geekster.FoodDeliveryProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food_item_tbl")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int foodId;
    @Length(min=3,max=10,message ="please enter the fooditem name with the length of 3 - 10")
    private String name;
    @Length(min=3,max=60,message ="please enter the length between 3 to 60 characters")
    private String description;

}
