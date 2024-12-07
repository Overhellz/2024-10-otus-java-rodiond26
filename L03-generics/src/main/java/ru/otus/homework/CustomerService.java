package ru.otus.homework;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    private Comparator<Customer> customerComparator = Comparator.comparingLong(Customer::getScores);
    private final NavigableMap<Customer, String> customerData = new TreeMap<>(customerComparator);

    public Map.Entry<Customer, String> getSmallest() {
        return customerData.entrySet()
                .stream()
                .findFirst()
                .map(this::copy)
                .orElse(null);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return customerData.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getScores() > customer.getScores())
                .findFirst()
                .map(this::copy)
                .orElse(null);
    }

    public void add(Customer customer, String data) {
        customerData.put(Customer.copy(customer), data);
    }

    private Map.Entry<Customer, String> copy(Map.Entry<Customer, String> entry) {
        return Map.entry(Customer.copy(entry.getKey()), entry.getValue());
    }
}
