package com.example.Backend.Controller;

import com.example.Backend.Entity.Garniture;
import com.example.Backend.Entity.Order_Food_item;
import com.example.Backend.Entity.FoodsOrders;
import com.example.Backend.Services.*;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/Order_food_item")
@RestController
public class OrderController {
    @Autowired
    private NotificationService notificationService;
    private OrderFoodService orderFoodService;
    private OrderFoodItemService orderFoodItemService;
    private GarnitureService garnitureService;

    public OrderController(OrderFoodService orderFoodService, OrderFoodItemService orderFoodItemService,
            GarnitureService garnitureService) {
        this.orderFoodService = orderFoodService;
        this.orderFoodItemService = orderFoodItemService;
        this.garnitureService = garnitureService;
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @PostMapping("/add")
    public FoodsOrders AddOrder(@RequestBody FoodsOrders order) {
        // Déléguer toute la logique de création au service
        return orderFoodService.createOrderWithItemsAndGarnitures(order);
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @GetMapping("/userorders")
    public List<FoodsOrders> getUserOrders(@RequestParam int id) {
        return orderFoodService.getUserOrders(id);
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @GetMapping("/getOrders")
    public List<FoodsOrders> getOrders() {
        return orderFoodService.getOrders();
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @PutMapping("/orderReady/{id}")
    public FoodsOrders setOrderReady(@PathVariable int id) {
        FoodsOrders res = orderFoodService.UpdateOrder(id);
        notificationService.sendNotification("Food ready", "", res.getEmployee().getDeviceToken());
        return res;
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable int id) {
        FoodsOrders order = orderFoodService.getOrder(id);
        for (Order_Food_item orderfood : order.getFoodOrders()) {
            for (Garniture garniture : orderfood.getGarniture()) {
                garnitureService.delete(garniture.getGarnitureid());

            }
            orderFoodItemService.deleteOrderfood(orderfood.getOrder_Food_Item_id());

        }
        orderFoodService.deleteOrder(id);
    }

}
