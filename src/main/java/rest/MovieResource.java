package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import entities.Movie;
import facades.EmployeeFacade;
import facades.FacadeExample;
import facades.MovieFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/movie")
public class MovieResource {

    private final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final MovieFacade mf = MovieFacade.getMovieFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @GET
    @Path("/all")
    @Produces("application/json")
    public String getAllMovies() {
        List<MovieDTO> movieDTOS = mf.getAllMovies();

        return GSON.toJson(movieDTOS);
    }

    @GET
    @Path("/count")
    @Produces("application/json")
    public String getCountOfMovies(){
        Long count = mf.getCountOfMovies();

        return GSON.toJson(count);
    }
    @GET
    @Path("/title/{title}")
    @Produces("application/json")
    public String getTitleByTitle(@PathParam("title")String title){
        List<MovieDTO> movieDTOS = mf.getMoviesByTitle(title);

        return GSON.toJson(movieDTOS);
    }

    @GET
    @Path("/id/{id}")
    @Produces("application/json")
    public String getMovieById(@PathParam("id")int id){
        MovieDTO movieDTO = mf.getMovieById(id);

        return GSON.toJson(movieDTO);
    }
}