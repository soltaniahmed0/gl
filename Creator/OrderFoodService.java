package com.example.Backend.Services;

import com.example.Backend.Entity.FoodsOrders;
import com.example.Backend.Entity.Garniture;
import com.example.Backend.Entity.Order_Food_item;
import com.example.Backend.Repository.OrderFoodItemRepository;
import com.example.Backend.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Service
public class OrderFoodService {
    List<FoodsOrders> FoodsOrders;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderFoodItemService orderFoodItemService;
    @Autowired
    private GarnitureService garnitureService;

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

    public FoodsOrders createOrderWithItemsAndGarnitures(FoodsOrders order) {
        // 1. Définir la date de la commande
        order.setDate(LocalDate.now());

        // 2. Sauvegarder la commande principale
        FoodsOrders savedOrder = orderRepository.save(order);

        // 3. Créer et sauvegarder les items de la commande
        for (Order_Food_item orderFoodItem : order.getFoodOrders()) {
            orderFoodItem.setOrders_id(savedOrder.getOrder_food_id());
            Order_Food_item savedOrderFoodItem = orderFoodItemService.saveOrderfood(orderFoodItem);

            // 4. Créer et sauvegarder les garnitures liées à cet item de commande
            for (Garniture garniture : orderFoodItem.getGarniture()) {
                // Créer un objet Garniture et l'ajouter
                Garniture newGarniture = new Garniture(garniture.getName(), garniture.isChecked(),
                        savedOrderFoodItem.getOrder_Food_Item_id());
                garnitureService.addgar(newGarniture);
            }
        }

        // 5. Retourner la commande avec les objets associés sauvegardés
        return savedOrder;
    }

    public List<FoodsOrders> getOrders() {
        return orderRepository.findAll();
    }

    public FoodsOrders getOrder(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public FoodsOrders UpdateOrder(int id) {
        FoodsOrders existingOrder = getOrder(id);
        existingOrder.setReady(true);
        return orderRepository.save(existingOrder);

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

        orderRepository.deleteById(id);

    }

}
