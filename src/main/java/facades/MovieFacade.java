package facades;

import dtos.EmployeeDTO;
import dtos.MovieDTO;
import entities.Employee;
import entities.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class MovieFacade {
    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    public MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */

    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createMovie(){
        ArrayList<String> actors = new ArrayList<>();
        actors.add("Søren Tissemand");
        actors.add("August Bigus");
        actors.add("Alex TheLarge");
        actors.add("Kasper Dongus");
        Movie movie = new Movie(1998,"Mr. Jacob",actors);
        ArrayList<String> actors1 = new ArrayList<>();
        actors1.add("Søren");
        actors1.add("August");
        actors1.add("Alex");
        actors1.add("Kasper");
        Movie movie1 = new Movie(2002,"The stribe",actors1);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(movie);
            em.persist(movie1);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    public List<MovieDTO> getAllMovies(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m",Movie.class);
            List<Movie> movies = query.getResultList();
            List<MovieDTO> movieDTOS = MovieDTO.getDtos(movies);
            return  movieDTOS;
        }finally {
            em.close();
        }
    }

    public long getCountOfMovies(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Long> query = em.createQuery("SELECT count(m) FROM Movie m",Long.class);
            Long count = query.getSingleResult();
            return count;
        }finally {
            em.close();
        }
    }

    public List<MovieDTO> getMoviesByTitle(String title){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m where m.title =:title",Movie.class);
            query.setParameter("title", title);
            List<Movie> movies = query.getResultList();
            List<MovieDTO> movieDTOS = MovieDTO.getDtos(movies);
            return movieDTOS;
        }finally {
            em.close();
        }
    }

    public MovieDTO getMovieById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m where m.id =:id",Movie.class);
            query.setParameter("id",id);
            Movie movie = query.getSingleResult();
            MovieDTO movieDTO = new MovieDTO(movie);
            return movieDTO;
        }finally {
            em.close();
        }
    }
}
