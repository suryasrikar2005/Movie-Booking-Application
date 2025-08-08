package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.MovieDTO;
import com.example.MovieBookingApplication.Repository.MovieRepository;
import com.example.MovieBookingApplication.entity.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public Movie addMovie(MovieDTO movieDTO){
        Movie movie=new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setDuration(movieDTO.getDuration());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setLanguage(movieDTO.getLanguage());

        return movieRepository.save(movie);
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    public List<Movie> getMovieByGenre(String genre){
        Optional<List<Movie>> listOfMovieBox= movieRepository.findByGenre(genre);

        if(listOfMovieBox.isPresent()){
            return listOfMovieBox.get();
        }
        else throw new RuntimeException("No movies found for genre"+genre);
    }
    public List<Movie> getMovieByLanguage(String language){
        Optional<List<Movie>> listOfMovieBox= movieRepository.findByLanguage(language);

        if(listOfMovieBox.isPresent()){
            return listOfMovieBox.get();
        }
        else throw new RuntimeException("No movies found for language"+language);
    }
    public List<Movie> getMovieByTitle(String title) {
        Optional <List<Movie>> movieBox = movieRepository.findByName(title);
        return movieBox.orElseThrow(() -> new RuntimeException("No movies found by title " + title));
    }
    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Movie found for the id " + id));

        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setDuration(movieDTO.getDuration());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setLanguage(movieDTO.getLanguage());

        return movieRepository.save(movie); // Save and return updated movie
    }
    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

}
