package com.example.Backend.Controller;



import com.example.Backend.Entity.Food;

import com.example.Backend.Services.Cross_Origin;
import com.example.Backend.Services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/food")
@RestController
public class FoodController {

    private FoodService foodService;


    @Autowired
    public FoodController(FoodService foodService){
        this.foodService=foodService;
    }
    @CrossOrigin(origins = Cross_Origin.url)
    @GetMapping("/getfood")
    public Food getFood(@RequestParam int id){

        return foodService.getFood(id);

    }

    @CrossOrigin(origins = Cross_Origin.url)
    @GetMapping("/getfoods")

    public List<Food> getFood(){
        return foodService.getFoodList();
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @PostMapping("/add")
    public void add(@RequestBody Food FoodStrings){
        foodService.addFood(FoodStrings);
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @PutMapping("/update")
    public void update(@RequestBody Food foodStrings){
        foodService.update(foodStrings);

    }
    @CrossOrigin(origins = Cross_Origin.url)
    @DeleteMapping("/delete")
    public String deleteFood(@RequestParam int id){
        foodService.deleteFood(id);
        return "aaaaaaaa";
    }

}
