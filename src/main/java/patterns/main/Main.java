package patterns.main;

import patterns.task.Customer.CustomerService;
import patterns.task.Movie.Movie;
import patterns.task.Movie.MovieService;
import patterns.task.PriceCodes.Children;
import patterns.task.PriceCodes.NewRelease;
import patterns.task.PriceCodes.Regular;

import java.util.Arrays;
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
                case 4 -> main.editMovie();
                case 0 -> {
                    main.saveAndLeave();
                    return;
                }
                default -> System.out.println("No such option in menu");
            }
        }
    }

    private void editMovie() {
        List<Movie> movies = searchMovieByMenu();
        if (movies.isEmpty()) {
            System.out.println("No movies to be edited");
            return;
        }
        System.out.println("Pick Movie to be edited:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(i + " - " + movies.get(i).toString());
        }
        movieService.updateMovie(
                fillMovieInfo(
                        movies.get(scanner.nextInt())
                ));
    }

    private Movie fillMovieInfo(Movie movie) {
        System.out.print("""
                input field distinguished by line, args by whitespaces:
                Country of production
                Price code
                Actors
                Short description
                """);
        String COP = scanner.next();
        COP += scanner.nextLine();
        String priceCode = scanner.nextLine();
        List<String> actors = Arrays.stream(scanner.nextLine().split(" ")).toList();
        String description = scanner.nextLine();
        switch (priceCode.toLowerCase()) {
            case "children" -> movie.setPriceCode(new Children());
            case "regular" -> movie.setPriceCode(new Regular());
            case "new", "newrelease" -> movie.setPriceCode(new NewRelease());
        }
        movie.setCountryOfProduction(COP);
        movie.setActors(actors);
        movie.setShortDescription(description);
        return movie;
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


    private List<Movie> searchMovieByMenu() {
        System.out.print("""
                Find movie by:
                1 - title
                2 - country of production
                3 - actor
                """);
        List<Movie> movies = List.of();
        int choice = scanner.nextInt();
        String param = scanner.next();
        param += scanner.nextLine();
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
        return movies;
    }

    private void saveAndLeave() {
        movieService.close();
        customerService.close();
    }

    private void MainMenu() {
        System.out.println("""
                1 - Output all Films
                2 - Add new Film
                3 - Search Film By
                4 - Edit film info
                """);
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