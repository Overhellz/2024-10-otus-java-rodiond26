package ru.otus.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerTest.class);

    private Customer customer;

    @Before
    public void setUp() {
        log.info("Start test");
        customer = new Customer();
    }

    @Test
    public void getFullName_whenCorrectValuesTest() {
        String testName = "getFullName_whenCorrectValuesTest";

        String firstName = "firstName";
        String lastName = "lastName";
        customer = new Customer(1, firstName, lastName);

        if ("lastName firstName".equals(customer.getFullName())) {
            log.info("Test {} is passed.", testName);
        } else {
            log.error("Test {} is failed.", testName);
            throw new RuntimeException("Test is failed");
        }
    }

    @Test
    public void getFullName_whenIncorrectValuesTest() {
        String testName = "getFullName_whenIncorrectValuesTest";

        String firstName = "firstName";
        String lastName = "lastName";
        customer = new Customer(2, lastName, firstName);

        if ("lastName firstName".equals(customer.getFullName())) {
            log.info("Test {} is passed.", testName);
        } else {
            log.error("Test {} is failed.", testName);
            throw new RuntimeException("Test is failed");
        }
    }

    @Test
    public void getFullName_whenNullValuesTest() {
        String testName = "getFullName_whenNullValuesTest";

        customer = new Customer(2, null, null);

        if ("null null".equals(customer.getFullName())) {
            log.info("Test {} is passed.", testName);
        } else {
            log.error("Test {} is failed.", testName);
            throw new RuntimeException("Test is failed");
        }
    }

    @After
    public void tearDown() {
        customer = null;
        log.info("End test");
    }
}
