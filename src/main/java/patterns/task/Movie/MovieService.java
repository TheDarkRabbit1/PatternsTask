package patterns.task.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieService {
    private final MovieDao movieDao = MovieDao.getInstance();
    private static MovieService instance;
    public synchronized static MovieService getInstance(){
        if(instance == null){
            instance = new MovieService();
        }
        return instance;
    }
    public List<Movie> getMovies(){
        return movieDao.getMovies();
    }
    public void addMovie(Movie movie){
        movieDao.addMovie(movie);
    }
    public List<Movie> getMovieByTitle(String title) {
        return movieDao.getMovies().stream()
                .filter(movie -> movie.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    public List<Movie> getMovieByCOP(String cop) {
        return movieDao.getMovies().stream()
                .filter(movie -> movie.getCountryOfProduction().equals(cop))
                .collect(Collectors.toList());
    }

    public List<Movie> getMovieByActor(String actor) {
        return movieDao.getMovies().stream()
                .filter(movie -> movie.getActors().contains(actor))
                .collect(Collectors.toList());
    }
    public void close(){
        movieDao.close();
    }
}
