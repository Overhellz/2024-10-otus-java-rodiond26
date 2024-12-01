package ru.otus.homework;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    private Comparator<Customer> customerComparator = Comparator.comparing(Customer::getScores);
    private NavigableMap<Customer, String> customerData = new TreeMap<>(customerComparator);

    public Map.Entry<Customer, String> getSmallest() {
        return customerData.firstEntry() == null
                ? null
                : Map.entry(Customer.copy(customerData.firstEntry().getKey()), customerData.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        if (!isSorted(customerData)) {
            customerData = copy(customerData);
        }

        return customer == null
                ? null
                : customerData.ceilingEntry(customer);
    }

    public void add(Customer customer, String data) {
        customerData.put(Customer.copy(customer), data);
    }

    @Override
    public String toString() {
        return "CustomerService{" + "customerData=" + customerData + '}';
    }

    private boolean isSorted(NavigableMap<Customer, String> map) {
        Customer previousKey = null;
        for (Map.Entry<Customer, String> entry : map.entrySet()) {
            if (previousKey != null && entry.getKey().getScores() < previousKey.getScores()) {
                return false;
            }
            previousKey = entry.getKey();
        }
        return true;
    }

    private NavigableMap<Customer, String> copy(NavigableMap<Customer, String> map) {
        NavigableMap<Customer, String> resultMap = new TreeMap<>(customerComparator);
        map.forEach((key, value) -> resultMap.put(key, value));
        return resultMap;
    }
}
