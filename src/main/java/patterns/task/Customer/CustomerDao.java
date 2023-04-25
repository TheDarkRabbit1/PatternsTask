package patterns.task.Customer;

import patterns.task.Movie.MovieDao;

import java.io.*;
import java.util.List;

public class CustomerDao {
    private static CustomerDao instance = new CustomerDao();
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
    }

    private void createEmptySaveFile() {
        try(FileOutputStream fileOutputStream = new FileOutputStream("customer_list.ch");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(List.of());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomers() {
        return this.customers;
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
