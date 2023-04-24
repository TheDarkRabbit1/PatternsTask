package patterns.task.Movie;

import patterns.task.PriceCodes.MovieType;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private String title;
    private String countryOfProduction;
    private List<String> actors;
    private String shortDescription;
    private MovieType priceCode;
    private Movie() {
    }

    public String getCountryOfProduction() {
        return countryOfProduction;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getActors() {
        return actors;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public MovieType getPriceCode() {
        return priceCode;
    }

    public static class Builder {
        private String title;
        private String countryOfProduction;
        private List<String> actors;
        private MovieType priceCode;
        private String shortDescription;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder countryOfProduction(String countryOfProduction) {
            this.countryOfProduction = countryOfProduction;
            return this;
        }

        public Builder actors(List<String> actors) {
            this.actors = actors;
            return this;
        }

        public Builder priceCode(MovieType priceCode) {
            this.priceCode = priceCode;
            return this;
        }

        public Builder shortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Movie build() {
            Movie movie = new Movie();
            movie.actors = actors;
            movie.countryOfProduction = countryOfProduction;
            movie.title = title;
            movie.priceCode = priceCode;
            movie.shortDescription = shortDescription;
            return movie;
        }
    }
}