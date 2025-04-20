package com.example.Backend.Factory;

import com.example.Backend.Entity.Role;
import org.springframework.stereotype.Component;
public class EmployeeFactoryProvider {

    public static EmployeeFactory getFactory(Role role) {
        return switch (role) {
            case ADMIN -> new AdminFactory();
            case Receptionist -> new ReceptionistFactory();
            case Restaurantcashier -> new RestaurantCashierFactory();
            case SnackBarcashier -> new SnackBarCashierFactory();
            case EventManager -> new EventManagerFactory();
            default -> new Employee();
        };
    }
}
