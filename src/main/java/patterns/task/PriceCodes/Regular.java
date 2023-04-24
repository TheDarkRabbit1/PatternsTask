package patterns.task.PriceCodes;

import patterns.task.PriceCodes.MovieType;

public class Regular implements MovieType {
    @Override
    public double getAmount(int daysRented) {
        double res = 2;
        if (daysRented > 2)
            res += (daysRented - 2) * 1.5;
        return res;
    }
}
