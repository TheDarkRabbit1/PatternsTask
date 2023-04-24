package patterns.task.Movie;


import java.io.*;
import java.util.List;

public class MovieDao{
    private static MovieDao instance = new MovieDao();
    private List<Movie> movies;
    public static MovieDao getInstance(){
        return instance;
    }
    private MovieDao() {
        try {
            FileInputStream fileInputStream = new FileInputStream("movie_list.ch");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            this.movies = (List<Movie>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    List<Movie> getMovies() {
        return this.movies;
    }

    void saveMovieList(List<Movie> list){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("movie_list.ch");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
