package patterns.task.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDao {
    private static CustomerDao instance;
    private List<Customer> customers;
    public static CustomerDao getInstance(){
        if(instance == null){
            instance = new CustomerDao();
        }
        return instance;
    }
    private CustomerDao() {
        try (FileInputStream fileInputStream = new FileInputStream("customer_list.ch");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            this.customers = (List<Customer>) objectInputStream.readObject();
        } catch (FileNotFoundException e){
            createEmptySaveFile();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (this.customers==null)
            customers=new ArrayList<>();
    }

    private void createEmptySaveFile() {
        try(FileOutputStream fileOutputStream = new FileOutputStream("customer_list.ch");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(Collections.EMPTY_LIST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }
    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }
    public void removeCustomer(Customer customer){
        this.customers.remove(customer);
    }

    public void close(){
        try(FileOutputStream fileOutputStream = new FileOutputStream("customer_list.ch");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(this.customers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
