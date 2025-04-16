package com.example.Backend.Services;

import com.example.Backend.Entity.Food;

import com.example.Backend.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getFoodList() {
        return foodRepository.findAll();
    }

    public void addFood(Food f) {
        Food food = new Food(f.getFoodName(), f.getImg(), f.getPrice(), f.getCat(), f.available, f.getDescription());

        foodRepository.save(food);
        // foodList.add(new
        // Food(n.getFoodID(),n.getFoodName(),n.getImg(),n.getPrice(),_category(n.getCat()),n.available));
    }

    public void deleteFood(int id) {
        foodRepository.deleteById(id);
    }

    public Food getFood(int id) {
        return foodRepository.findById(id).orElse(null);
    }

    public Food update(Food foodStrings) {
        // Food foodForm=new
        // Food(foodStrings.getFoodID(),foodStrings.getFoodName(),foodStrings.getImg(),foodStrings.getPrice(),_category(foodStrings.getCat()),foodStrings.isAvailable());
        Food existingOrder = getFood(foodStrings.getFoodID());
        existingOrder = foodStrings;

        return foodRepository.save(existingOrder);

    }

}
