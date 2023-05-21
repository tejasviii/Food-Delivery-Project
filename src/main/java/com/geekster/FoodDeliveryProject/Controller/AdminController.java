package com.geekster.FoodDeliveryProject.Controller;

import com.geekster.FoodDeliveryProject.Model.Admin;
import com.geekster.FoodDeliveryProject.Model.FoodItem;
import com.geekster.FoodDeliveryProject.Model.Order;
import com.geekster.FoodDeliveryProject.Service.AdminService;
import com.geekster.FoodDeliveryProject.Service.FoodItemService;
import com.geekster.FoodDeliveryProject.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    FoodItemService foodItemService;
    @Autowired
    OrderService orderService;

    @PostMapping(value = "/creat-admin")
    public ResponseEntity addAdmin(@Valid @RequestBody Admin admindetails){
        return adminService.savedata(admindetails);
    }
    @GetMapping(value = "/getadmin")
    public List<Admin> getAll(){
        return adminService.showAll();
    }
    @PostMapping(value = "/create-food-item")
    public ResponseEntity createfood(@RequestBody FoodItem foodItem){
        foodItemService.savefood(foodItem);
        return new ResponseEntity<>("Food Item was created by the Admin", HttpStatus.CREATED);
    }
    @GetMapping(value = "/getallfood")
    public ResponseEntity getallfood(){
        List<FoodItem> foodItems = foodItemService.getall();
        return new ResponseEntity<>("Admin getting all the foods - "+foodItems,HttpStatus.OK);
    }
    @DeleteMapping(value = "/deletefoodbyid")
    public ResponseEntity deletefood(@RequestParam Integer foodId){
        if(foodItemService.getfoodbyId(foodId)!=null) {
            foodItemService.deletefood(foodId);
        }else{
            return new ResponseEntity<>("FoodItem with the following id is not present in the database",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("FoodItem is deleted successfully",HttpStatus.ACCEPTED);
    }
    @PutMapping(value = "/update-food-details")
    public ResponseEntity updatefood(@RequestParam Integer foodId, @Valid @RequestBody FoodItem foodItem){
        List<FoodItem> foodItems = adminService.findfoodItems();
        for(FoodItem food : foodItems){
            if(food.getFoodId()==foodId){
                food.setName(foodItem.getName());
                food.setDescription(foodItem.getDescription());
                foodItemService.savefood(food);
                return new ResponseEntity<>("Food data has been updated",HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>("Fooditem with the following id has not been found",HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/getOrderbyorderid")
    public ResponseEntity orderget(@RequestParam Integer orderid){
        List<Order> orderList = orderService.getall();
        for(Order order : orderList){
            if(order.getOrderId()==orderid){
                return new ResponseEntity("order which was given by this order id is -"+order.getFoodId().getName(),HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>("order with the following order id has not been found ",HttpStatus.NOT_FOUND);
    }
}
