package patterns.task.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieService {
    private static MovieService instance;
    private final MovieDao movieDao = MovieDao.getInstance();

    public synchronized static MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }
        return instance;
    }

    public List<Movie> getMovies() {
        return movieDao.getMovies();
    }

    public void addMovie(Movie movie) {
        if (movieDao.getMovies().stream().anyMatch(m -> m.getTitle().equals(movie.getTitle()))) {
            System.out.println("Movie with this title already exists");
            return;
        }
        movieDao.addMovie(movie);
    }

    public void updateMovie(Movie newMovie) {
        Movie movieByTitle = movieDao.getMovies().stream()
                .filter(m -> m.getTitle().equalsIgnoreCase(newMovie.getTitle()))
                .findFirst()
                .orElseThrow();
        movieDao.removeMovie(movieByTitle);
        movieDao.addMovie(newMovie);
    }

    public List<Movie> getMovieByTitle(String title) {
        return movieDao.getMovies().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> getMovieByCOP(String cop) {
        return movieDao.getMovies().stream()
                .filter(movie -> movie.getCountryOfProduction().toLowerCase().contains(cop.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> getMovieByActor(String actor) {
        return movieDao.getMovies().stream()
                .filter(movie -> movie.getActors().contains(actor))
                .collect(Collectors.toList());
    }
    public List<Movie> getMovieByGenre(String param) {
        return movieDao.getMovies().stream()
                .filter(m-> m.getGenre()
                        .getGenreName()
                        .equalsIgnoreCase(param))
                .collect(Collectors.toList());
    }
    public void close() {
        movieDao.close();
    }
}
