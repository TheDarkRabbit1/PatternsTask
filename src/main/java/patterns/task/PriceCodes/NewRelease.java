package patterns.task.PriceCodes;

public class NewRelease implements MovieType {
    @Override
    public double getAmount(int daysRented) {
        return daysRented * 3;
    }
}
