package ru.otus.homework;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    private NavigableMap<Customer, String> customerData = new TreeMap<>(Comparator.comparing(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return customerData.firstEntry() == null
                ? null
                : Map.entry(Customer.copy(customerData.firstEntry().getKey()), customerData.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        NavigableMap<Customer, String> newCustomerData = new TreeMap<>(Comparator.comparing(Customer::getScores));
        newCustomerData.putAll(customerData);
        customerData = newCustomerData;

        return customerData == null
                ? null
                : customerData.ceilingEntry(customer);
    }

    public void add(Customer customer, String data) {
        customerData.put(customer, data);
    }

    @Override
    public String toString() {
        return "CustomerService{" + "customerData=" + customerData + '}';
    }
}
