package patterns.task.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    private static final CustomerDao customerDao = CustomerDao.getInstance();
    public static CustomerService instance;
    public synchronized static CustomerService getInstance(){
        if (instance==null){
            instance = new CustomerService();
        }
        return instance;
    }
    public List<Customer> getCustomers(){
        return customerDao.getCustomers();
    }
    public void addCustomer(Customer customer){
        customerDao.addCustomer(customer);
    }
    public void removeCustomer(String name){
        customerDao.removeCustomer(name);
    }
    public Optional<Customer> getCustomerByName(String name){
        return customerDao.getCustomers().stream()
                .filter(customer -> customer.getName().equals(name))
                .findFirst();
    }
    public void close(){
        customerDao.close();
    }
}
