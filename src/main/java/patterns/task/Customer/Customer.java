package patterns.task.Customer;

import patterns.task.Rental;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {
    private final String name;
    private final List<Rental> rentals;
    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", rentals=" + rentals +
                '}';
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        for (Rental each : rentals) {
            double thisAmount = each.getAmount();
            frequentRenterPoints = each.getFrequentRenterPoints(frequentRenterPoints);
            //show figures for this rental
            result.append( "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n");
            totalAmount += thisAmount;
        }
        //add footer lines
        result.append("Amount owed is " + totalAmount + "\n");
        result.append("You earned " + frequentRenterPoints + " frequent renter points");
        return result.toString();
    }

}
