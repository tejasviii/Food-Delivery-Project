package com.geekster.FoodDeliveryProject.Service;

import com.geekster.FoodDeliveryProject.Dao.IFoodItemRepo;
import com.geekster.FoodDeliveryProject.Dao.IOrderRepo;
import com.geekster.FoodDeliveryProject.Dao.IUserRepo;
import com.geekster.FoodDeliveryProject.Model.FoodItem;
import com.geekster.FoodDeliveryProject.Model.Order;
import com.geekster.FoodDeliveryProject.Model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    IOrderRepo orderRepo;
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IFoodItemRepo foodItemRepo;
    public ResponseEntity saveorders(String orderdetails) {
        Order order= stringToJson(orderdetails);
        orderRepo.save(order);
        return new ResponseEntity<>("Data saved successfully", HttpStatus.CREATED);
    }

    public List<Order> getall() {
        return orderRepo.findAll();
    }
    public Order stringToJson(String order){
        JSONObject json = new JSONObject(order);
        Order order1 = new Order();
        order1.setOrderId(json.getInt("orderId"));
        User user = userRepo.findById(json.getInt("userid")).get();
        order1.setUserid(user);
        FoodItem foodItem = foodItemRepo.findById(json.getInt("foodId")).get();
        order1.setFoodId(foodItem);
        return order1;
    }
    public Order setOrder(String orderrequest){
        JSONObject jsonObject=new JSONObject(orderrequest);
        Order order1=new Order();
        String userId= jsonObject.getString("userId");
        User user1=userRepo.findById(Integer.valueOf(userId)).get();
        order1.setUserid(user1);
        String foodId= jsonObject.getString("foodId");
        FoodItem food1=foodItemRepo.findById(Integer.valueOf(foodId)).get();
        order1.setFoodId(food1);

        return order1;
    }

    public ResponseEntity<String> createOrder(String orderrequest) {
        JSONObject errorList=validateOrder(orderrequest);
        if(!errorList.isEmpty()){
            return new ResponseEntity<>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }
        else{
            Order order1=setOrder(orderrequest);
            Order order2=orderRepo.save(order1);
            return new ResponseEntity<>("order created at orderid -  "+order2.getOrderId(),HttpStatus.OK);
        }
    }
    private JSONObject validateOrder(String orderrequest) {
        JSONObject object=new JSONObject(orderrequest);
        JSONObject errorList=new JSONObject();
        if(!object.has("userId")){
            errorList.put("userId","missing parameter");
        }
        else {
            String userId = object.getString("userId");
            User user1=userRepo.findById(Integer.valueOf(userId)).get();
            if(user1==null){
                errorList.put("userId","User by userId does not exists in the database please enter the correct user");
            }
        }
        if(!object.has("foodId")){
            errorList.put("foodId","missing parameter");
        }
        else {
            String foodId = object.getString("foodId");
            FoodItem food1=foodItemRepo.findById(Integer.valueOf(foodId)).get();
            if(food1==null){
                errorList.put("foodId","Food item does not exists please type another for placing the order");
            }
        }
        return errorList;
    }

    public ResponseEntity<String> getorder(String orderId) {
        List<Order> orders=new ArrayList<>();
        if(orderId==null){
            orders=orderRepo.findAll();
        }
        else{
            Order order1=orderRepo.findById(Integer.valueOf(orderId)).get();
            orders.add(order1);
        }
        return new ResponseEntity<>(orders.toString(), HttpStatus.OK);
    }
}
