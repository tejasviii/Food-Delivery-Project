package com.geekster.FoodDeliveryProject.Service;

import com.geekster.FoodDeliveryProject.Dao.IAdminRepo;
import com.geekster.FoodDeliveryProject.Dao.IFoodItemRepo;
import com.geekster.FoodDeliveryProject.Model.Admin;
import com.geekster.FoodDeliveryProject.Model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;
    @Autowired
    IFoodItemRepo foodItemRepo;
    public ResponseEntity savedata(Admin admin){
        adminRepo.save(admin);
        return new ResponseEntity<>("Save data", HttpStatus.CREATED);
    }
    public List<Admin> showAll(){
        return adminRepo.findAll();
    }
    public FoodItem findfood(int foodid){
        return foodItemRepo.findById(foodid).get();
    }

    public List<Admin> getadmins() {
        return adminRepo.findAll();
    }

    public List<FoodItem> findfoodItems() {
        return foodItemRepo.findAll();
    }
}