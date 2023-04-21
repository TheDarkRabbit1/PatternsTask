package patterns.task;

public class Children implements MovieType {
    @Override
    public double getAmount(int daysRented) {
        double res = 1.5;
        if (daysRented > 3)
            res += (daysRented - 3) * 1.5;
        return res;
    }
}
