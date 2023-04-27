package patterns.task.Movie.PriceCodes;

import java.io.Serializable;

public interface MovieType extends Serializable {
    double getAmount(int daysRented);
}
