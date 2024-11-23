package ru.otus.homework;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CustomerService {

    private final SortedMap<Customer, String> customerData = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        return customerData.entrySet().stream()
                .filter(entry -> entry.getKey().equals(customerData.firstEntry().getKey()))
                .findFirst()
                .map(optionalEntry -> Map.entry(Customer.copy(optionalEntry.getKey()), optionalEntry.getValue()))
                .orElse(null);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return customerData.entrySet().stream()
                .filter(entry -> entry.getKey() != null)
                .filter(entry -> entry.getKey().getScores() > customer.getScores())
                .findFirst()
                .map(optionalEntry -> Map.entry(Customer.copy(optionalEntry.getKey()), optionalEntry.getValue()))
                .orElse(null);
    }

    public void add(Customer customer, String data) {
        customerData.put(customer, data);
    }

    @Override
    public String toString() {
        return "CustomerService{" + "customerData=" + customerData + '}';
    }
}
