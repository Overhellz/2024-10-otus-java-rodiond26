package ru.otus.homework;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    private final Comparator<Customer> customerComparator = Comparator.comparingLong(Customer::getScores);
    private final NavigableMap<Customer, String> customerData = new TreeMap<>(customerComparator);

    public Map.Entry<Customer, String> getSmallest() {
        return customerData.firstEntry() == null
                ? null
                : Map.entry(Customer.copy(customerData.firstEntry().getKey()), customerData.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> higherEntry = customerData.higherEntry(customer);
        return higherEntry == null
                ? null
                : Map.entry(Customer.copy(higherEntry.getKey()), higherEntry.getValue());
    }

    public void add(Customer customer, String data) {
        customerData.put(Customer.copy(customer), data);
    }

    private Map.Entry<Customer, String> copy(Map.Entry<Customer, String> entry) {
        return Map.entry(Customer.copy(entry.getKey()), entry.getValue());
    }
}
