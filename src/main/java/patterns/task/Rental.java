package patterns.task;

import patterns.task.Movie.PriceCodes.NewRelease;
import patterns.task.Movie.Movie;

import java.io.Serializable;

public class Rental implements Serializable {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getAmount() {
        return getMovie().getPriceCode().getAmount(daysRented);
    }

    public int getFrequentRenterPoints(int frequentRenterPoints) {
        // add frequent renter points
        frequentRenterPoints++;
        // add bonus for a two-day new release rental
        if ((getMovie().getPriceCode().getClass() == NewRelease.class) && getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
