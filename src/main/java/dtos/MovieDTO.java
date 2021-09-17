package dtos;

import entities.Employee;
import entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {

    private Long id;
    private int year;
    private String title;
    private ArrayList<String> actors;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.actors = movie.getActors();
    }

    public static List<MovieDTO> getDtos(List<Movie> movies){
        List<MovieDTO> movieDtos = new ArrayList();
        movies.forEach(mm->movieDtos.add(new MovieDTO(mm)));
        return movieDtos;
    }
}
