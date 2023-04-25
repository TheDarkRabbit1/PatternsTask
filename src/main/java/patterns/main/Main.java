package patterns.main;

import patterns.task.Customer.CustomerDao;
import patterns.task.Customer.CustomerService;
import patterns.task.Movie.Movie;
import patterns.task.Movie.MovieDao;
import patterns.task.Movie.MovieService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static final MovieService movieService = MovieService.getInstance();
    public static final CustomerService customerService = CustomerService.getInstance();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            main.MainMenu();
            switch (scanner.nextInt()) {
                case 1 -> main.getAllMovies();
                case 2 -> main.addMovie();
                case 3 -> main.searchMovieByMenu();
                case 0 -> {
                    main.saveAndLeave();
                    return;
                }
                default -> System.out.println("No such option in menu");
            }
        }
    }

    private void getAllMovies() {
        List<Movie> movies = movieService.getMovies();
        if (movies == null || movies.isEmpty()) {
            System.out.println("Currently no saved movies");
            return;
        }
        movies.forEach(movie -> System.out.println(movie.toString()));
    }

    private void addMovie() {
        System.out.println("Enter title for new movie: ");
        String title = scanner.next();
        title += scanner.nextLine();
        Movie movie = new Movie.Builder()
                .title(title)
                .build();
        movieService.addMovie(movie);
    }


    private void searchMovieByMenu() {
        System.out.println("find movie by:");
        System.out.println("1 - title");
        System.out.println("2 - country of production");
        System.out.println("3 - actor");
        List<Movie> movies = List.of();
        int choice = scanner.nextInt();
        String param = scanner.next();
        param+=scanner.nextLine();
        switch (choice) {
            case 1 -> movies = movieService.getMovieByTitle(param);
            case 2 -> movies = movieService.getMovieByCOP(param);
            case 3 -> movies = movieService.getMovieByActor(param);
            default -> System.out.println("Wrong Value");
        }
        if (movies.isEmpty()) {
            System.out.println("No Movie was found");
        } else {
            movies.forEach(movie -> System.out.println(movie.toString()));
        }
    }

    private void saveAndLeave() {
        movieService.close();
        customerService.close();
    }

    private void MainMenu() {
        System.out.println("1 - Output all Films");
        System.out.println("2 - Add new Film");
        System.out.println("3 - Search Film By");
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