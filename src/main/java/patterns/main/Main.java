package patterns.main;

import patterns.task.Customer.Customer;
import patterns.task.Customer.CustomerService;
import patterns.task.Movie.Genres.Comedy;
import patterns.task.Movie.Genres.Drama;
import patterns.task.Movie.Genres.Thriller;
import patterns.task.Movie.Movie;
import patterns.task.Movie.MovieService;
import patterns.task.Movie.PriceCodes.Children;
import patterns.task.Movie.PriceCodes.NewRelease;
import patterns.task.Movie.PriceCodes.Regular;
import patterns.task.Rental;

import java.util.*;

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
                case 3 -> main.searchMoviesByMenu();
                case 4 -> main.editMovie();
                case 5 -> main.customerMenu();
                case 0 -> {
                    main.saveAndLeave();
                    return;
                }
                default -> System.out.println("No such option in menu");
            }
        }
    }

    private void customerMenu() {
        System.out.print("""
                1 - customer list
                2 - add new customer
                3 - remove customer
                4 - get customer...
                """);
        switch (scanner.nextInt()) {
            case 1 -> customerService.getCustomers().forEach(System.out::println);
            case 2 -> addNewCustomerMenu();
            case 3 -> removeCustomer();
            case 4 -> getCustomerMenu();
            default -> System.out.println("no such option in list");
        }
    }

    private void addNewCustomerMenu() {
        System.out.println("Enter Customer name:");
        String name = scanner.next();
        name+=scanner.nextLine();
        customerService.addCustomer(new Customer(name, new ArrayList<>()));
    }

    private void getCustomerMenu() {
        System.out.println("Enter name of the customer:");
        String name = scanner.next();
        name+=scanner.nextLine();
        Optional<Customer> customer = customerService.getCustomerByName(name);
        if (customer.isEmpty()){
            System.out.println("no  customer with those params was found");
            return;
        }
        System.out.println("""
                1 - Add new rent
                2 - Get customer rent info
                """);
        switch (scanner.nextInt()){
            case 1 -> addRentForCustomerMenu(customer.get());
            case 2 -> System.out.println(customer.get().statement());
            default -> System.out.println("there is no such choice");
        }
    }
    private void addRentForCustomerMenu(Customer customer){
        List<Movie> movies = searchMoviesByMenu();
        System.out.println("Pick movie from the list:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(i+" - "+movies.get(i).toString());
        }
        int movieChoice = scanner.nextInt();
        System.out.println("enter days of rent:");
        int daysOfRent = scanner.nextInt();
        customer.getRentals().add(new Rental(movies.get(movieChoice),daysOfRent));
    }

    private void removeCustomer() {
        System.out.println("Enter name of the customer:");
        customerService.removeCustomer(scanner.nextLine());
    }


    private void editMovie() {
        List<Movie> movies = searchMoviesByMenu();
        if (movies.isEmpty()) {
            System.out.println("No movies to be edited");
            return;
        }
        System.out.println("Pick Movie to be edited:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(i + " - " + movies.get(i).toString());
        }
        movieService.updateMovie(
                fillMovieInfo(movies.get(scanner.nextInt())));
    }

    private Movie fillMovieInfo(Movie movie) {
        System.out.print("""
                input field distinguished by line, args by whitespaces:
                Country of production
                Genre
                Price code
                Actors
                Short description           
                """);
        String COP = scanner.next();
        COP += scanner.nextLine();
        String genreName = scanner.nextLine();
        String priceCode = scanner.nextLine();
        List<String> actors = Arrays.stream(scanner.nextLine().split(" ")).toList();
        String description = scanner.nextLine();

        switch (priceCode.toLowerCase()) {
            case "children" -> movie.setPriceCode(new Children());
            case "regular" -> movie.setPriceCode(new Regular());
            case "new", "newrelease" -> movie.setPriceCode(new NewRelease());
            default -> System.out.println("no such price code as:"+priceCode);
        }
        switch (genreName.toLowerCase()){
            case "comedy"->movie.setGenre(new Comedy());
            case "drama"->movie.setGenre(new Drama());
            case "thriller"->movie.setGenre(new Thriller());
            default -> System.out.println("no such genre as:"+genreName);
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


    private List<Movie> searchMoviesByMenu() {
        System.out.print("""
                Find movie by:
                1 - title
                2 - country of production
                3 - actor
                4 - genre
                """);
        List<Movie> movies = List.of();
        int choice = scanner.nextInt();
        String param = scanner.next();
        param += scanner.nextLine();
        switch (choice) {
            case 1 -> movies = movieService.getMovieByTitle(param);
            case 2 -> movies = movieService.getMovieByCOP(param);
            case 3 -> movies = movieService.getMovieByActor(param);
            case 4 -> movies = movieService.getMovieByGenre(param);
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
                1 - Output all Movies
                2 - Add new Movie
                3 - Search Movie By
                4 - Edit movie info
                5 - Customer menu
                0 - Save and exit
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