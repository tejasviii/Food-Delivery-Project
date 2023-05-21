package com.geekster.FoodDeliveryProject.Dao;

import com.geekster.FoodDeliveryProject.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodItemRepo extends JpaRepository<FoodItem,Integer> {
}
