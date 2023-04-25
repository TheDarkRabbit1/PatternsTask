package patterns.task.Movie;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieDao{
    private static MovieDao instance;
    private List<Movie> movies;
    public synchronized static MovieDao getInstance(){
        if(instance == null){
            instance = new MovieDao();
        }
        return instance;
    }

    private MovieDao() {
        try(FileInputStream fileInputStream = new FileInputStream("movie_list.ch");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            this.movies = (List<Movie>) objectInputStream.readObject();
        } catch (FileNotFoundException e){
         createNewEmptySaveFile();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (this.movies==null)
            movies=new ArrayList<>();
    }

    private void createNewEmptySaveFile() {
        try(FileOutputStream fileOutputStream = new FileOutputStream("customer_list.ch");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(Collections.EMPTY_LIST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void close(){
        try(FileOutputStream fileOutputStream = new FileOutputStream("movie_list.ch");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(this.movies);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
