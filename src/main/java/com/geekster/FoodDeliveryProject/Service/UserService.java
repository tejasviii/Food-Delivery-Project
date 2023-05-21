package com.geekster.FoodDeliveryProject.Service;

import com.geekster.FoodDeliveryProject.Dao.IUserRepo;
import com.geekster.FoodDeliveryProject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    public User addUser(User user) {
        return userRepo.save(user);
    }
    public List<User> getall() {
        return userRepo.findAll();
    }

    public ResponseEntity login(String name , String password){
        List<User> userList = userRepo.findAll();
        for(User user : userList){
            if(user.getName().equals(name)){
                if(user.getEmail().equals(password)){
                    return new ResponseEntity<>("User login successfully", HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("please check the user name and email ",HttpStatus.NOT_FOUND);
    }
}