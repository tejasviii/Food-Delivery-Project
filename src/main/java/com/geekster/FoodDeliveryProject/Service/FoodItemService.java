package com.geekster.FoodDeliveryProject.Service;

import com.geekster.FoodDeliveryProject.Dao.IFoodItemRepo;
import com.geekster.FoodDeliveryProject.Model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    IFoodItemRepo foodrepo;
    public FoodItem savefood(FoodItem foodItem) {
        return foodrepo.save(foodItem);

    }
    public List<FoodItem> getall(){
        return foodrepo.findAll();
    }
    public FoodItem getfoodbyId(Integer foodid){
        return foodrepo.findById(foodid).get();
    }
    public void deletefood(Integer foodid){
        foodrepo.deleteById(foodid);
    }
    public void updatefood(Integer foodid , FoodItem foodItem){
        FoodItem food = getfoodbyId(foodid);
        food.setName(foodItem.getName());
        food.setDescription(foodItem.getDescription());
    }

}
