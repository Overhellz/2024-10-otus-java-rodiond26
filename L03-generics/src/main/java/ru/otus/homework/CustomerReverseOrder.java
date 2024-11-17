package ru.otus.homework;

import java.util.LinkedList;
import java.util.List;

public class CustomerReverseOrder {

    private final List<Customer> customers = new LinkedList<>();

    public void add(Customer customer) {
        customers.addFirst(customer);
    }

    public Customer take() {
        Customer customer = customers.getFirst();
        customers.remove(customers.getFirst());
        return customer;
    }
}
