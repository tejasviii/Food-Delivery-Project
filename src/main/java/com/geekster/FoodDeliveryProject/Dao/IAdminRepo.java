package com.geekster.FoodDeliveryProject.Dao;

import com.geekster.FoodDeliveryProject.Model.Admin;
import com.geekster.FoodDeliveryProject.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Integer> {

}
