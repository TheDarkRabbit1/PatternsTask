package patterns.task.Customer;

import java.io.*;
import java.util.List;

public class CustomerDao {
    private List<Customer> customers;

    public CustomerDao() {
        try {
            FileInputStream fileInputStream = new FileInputStream("customer_list.ch");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            this.customers = (List<Customer>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    List<Customer> getCustomerList() {
        return this.customers;
    }

    void saveCustomerList(List<Customer> list){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("customer_list.ch");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
