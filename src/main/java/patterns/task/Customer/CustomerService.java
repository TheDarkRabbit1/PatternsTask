package patterns.task.Customer;

import java.util.List;

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
    public void close(){
        customerDao.close();
    }
}
