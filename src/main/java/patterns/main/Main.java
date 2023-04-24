package patterns.main;

import patterns.task.*;
import patterns.task.Customer.Customer;
import patterns.task.Customer.CustomerDao;
import patterns.task.Movie.MovieDao;
import patterns.task.PriceCodes.Children;
import patterns.task.PriceCodes.NewRelease;
import patterns.task.PriceCodes.Regular;
import patterns.task.Movie.Movie;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static final MovieDao movieDao = MovieDao.getInstance();
    public static final CustomerDao customerDao = CustomerDao.getInstance();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        while (true) {
            main.MainMenu();
            switch (scanner.nextInt()) {
                case 1 -> main.getAllFilms();
                case 2 -> main.addFilm();
                case 3 -> main.searchFilmByMenu();
                case 0 -> {return;}
                default -> System.out.println("No such option in menu");
            }
        }
    }
    private void getAllFilms(){

    }
    private void addFilm(){

    }
    private void searchFilmByMenu(){

    }
    private void MainMenu(){
        System.out.println("1 - Output all Films");
        System.out.println("2 - Add new Film");
        System.out.println("3 - Search Film By");
    }
    private void run() {
        List<Rental> rentals = List.of(
                new Rental(new Movie.Builder()
                        .priceCode(new Regular())
                        .title("Rambo")
                        .build(),1),
                new Rental(new Movie.Builder()
                        .title("Lord of the Rings")
                        .priceCode(new NewRelease())
                        .build(),4),
                new Rental(new Movie.Builder()
                        .title("Harry Potter")
                        .priceCode(new Children())
                        .build(),5)
        );
        Customer customer = new Customer("John Doe", rentals);
        String statement = customer.statement();
        System.out.println(statement);
    }
}

// Task:

// Уявімо, що нам треба додати нові фічі.
// По-перше, клієнт хоче виведення ще й у HTML.
// По-друге, клієнт хоче додати інші типи фільмів - наприклад, драми, комедії, трилери.
// По-третє, ми хочемо додати до фільмів нові характеристики (країна походження, короткий опис, режисер, актори)

// У поточну структуру ці правки лягають важко, тому нам доведеться її змінити.

// Створити можливість обліку декількох клієнтів, роботи з текстовими(!?) файлами для зберігання даних
// Програма повинна надавати можливості
// - перегляду каталога всіх фільмів,
// - додавання фільму,
// - пошуку за певними характеристиками
// - та ін.
// Створити зручне меню для доступу до функцій