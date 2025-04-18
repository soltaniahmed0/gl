
package com.example.Backend.Services;

import com.example.Backend.Entity.FoodsOrders;
import com.example.Backend.Entity.Order_Food_item;
import com.example.Backend.Repository.OrderFoodItemRepository;
import com.example.Backend.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderFoodService {
    List<FoodsOrders> FoodsOrders;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderFoodItemRepository orderFoodItemRepository;

    public OrderFoodService() {
        FoodsOrders = new ArrayList<FoodsOrders>();
    }

    public FoodsOrders saveOrder(FoodsOrders o) {
        FoodsOrders res = orderRepository.save(o);
        System.out.println(res);
        return res;
    }

    public List<FoodsOrders> saveOrders() {
        return orderRepository.saveAll(FoodsOrders);
    }

    public List<FoodsOrders> getOrders() {
        return orderRepository.findAll();
    }

    public FoodsOrders getOrder(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    // Méthode modifiée pour utiliser la méthode markAsReady de FoodsOrders
    public FoodsOrders UpdateOrder(int id) {
        FoodsOrders existingOrder = getOrder(id);
        if (existingOrder != null) {
            // Délégation de la responsabilité à la classe FoodsOrders
            existingOrder.markAsReady();
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public List<FoodsOrders> getUserOrders(int i) {
        return orderRepository.findByEmployee_Id(i);
    }

    public List<FoodsOrders> getOrders(int i) {
        return orderRepository.findAll();
    }

    public List<FoodsOrders> getnotReadyOrders() {
        return orderRepository.findByReady(false);
    }

    public List<FoodsOrders> getnotReadyUserOrders(int i) {
        return orderRepository.findByEmployee_IdAndReady(i, false);
    }

    public void deleteOrder(int id) {
        try {
            orderFoodItemRepository.deleteByOrdersId(id);
        } catch (Exception e) {
            System.out.println("error");
        }
        orderRepository.deleteById(id);
    }
}