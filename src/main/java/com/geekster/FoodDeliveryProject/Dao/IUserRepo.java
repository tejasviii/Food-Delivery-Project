package com.geekster.FoodDeliveryProject.Dao;

import com.geekster.FoodDeliveryProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepo extends JpaRepository<User,Integer> {


    List<User> findByName(String username);
}