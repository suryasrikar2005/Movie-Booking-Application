package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.ShowDTO;
import com.example.MovieBookingApplication.Repository.MovieRepository;
import com.example.MovieBookingApplication.Repository.ShowRepository;
import com.example.MovieBookingApplication.Repository.TheatreRepository;
import com.example.MovieBookingApplication.entity.Booking;
import com.example.MovieBookingApplication.entity.Movie;
import com.example.MovieBookingApplication.entity.Show;
import com.example.MovieBookingApplication.entity.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public Show createShow(ShowDTO showDTO){
        Movie movie=movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()-> new RuntimeException("No movies found for id"+showDTO.getMovieId()));

        Theatre theater = theatreRepository.findById(showDTO.getTheatreId())
                .orElseThrow(() -> new RuntimeException("No Theater Found for id " + showDTO.getTheatreId()));

        Show show = new Show();
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheatre(theater);

        return showRepository.save(show);
    }

    public List<Show>getAllShows(){
        return showRepository.findAll();
    }


    public List<Show> getShowsByMovie(Long movieid) {
        Optional<List<Show>> showListBox = showRepository.findByMovieId(movieid);
        if (showListBox.isPresent()) {
            return showListBox.get();
        } else {
            throw new RuntimeException("No shows available for the movie");
        }
    }


    public List<Show> getShowsByTheatre(Long theatreid) {
        Optional<List<Show>> showListBox = showRepository.findByTheatreId(theatreid);
        if (showListBox.isPresent()) {
            return showListBox.get();
        } else {
            throw new RuntimeException("No shows available in the theatre");
        }
    }

    public Show updateShow(Long id, ShowDTO showDTO) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Show available for the id " + id));

        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("No Movie Found for id " + showDTO.getMovieId()));

        Theatre theatre = theatreRepository.findById(showDTO.getTheatreId())
                .orElseThrow(() -> new RuntimeException("No Theater Found for id " + showDTO.getTheatreId()));

        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheatre(theatre);

        return showRepository.save(show);
    }

    public void deleteShow(Long id){
        if(!showRepository.existsById(id)){
            throw new RuntimeException("No Show available for the id"+id);
        }
        List<Booking>bookings = showRepository.findById(id).get().getBookings();
        if(bookings.isEmpty()){
            throw new RuntimeException("Can't delete show with existing booking");
        }
        showRepository.deleteById(id);
    }
}
