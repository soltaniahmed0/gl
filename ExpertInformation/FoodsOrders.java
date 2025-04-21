// Modification de la classe FoodsOrders pour y intégrer les méthodes selon le principe Expert en Information
package com.example.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class FoodsOrders {
    @Id
    @GeneratedValue
    private int order_food_id;
    private String order_date;
    private boolean ready;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToMany(mappedBy = "foodsOrders")
    @JsonIgnoreProperties("foodsOrders")
    private List<Order_Food_item> foodOrders;
    private LocalDate date;
    private String description;

    public FoodsOrders(String order_date, boolean ready, Employee employee) {
        this.order_date = order_date;
        this.ready = ready;
        this.employee = employee;
    }
    
    // Méthode déplacée du service: marquer la commande comme prête
    // Cette méthode manipule directement l'état interne de FoodsOrders
    public void markAsReady() {
        this.ready = true;
    }
    
   