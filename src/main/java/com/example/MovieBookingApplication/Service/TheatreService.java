package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.TheatreDTO;
import com.example.MovieBookingApplication.Repository.TheatreRepository;
import com.example.MovieBookingApplication.entity.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public Theatre addTheatre(TheatreDTO theatreDTO) {
        Theatre theatre = new Theatre();
        theatre.setTheatreName(theatreDTO.getTheatreName());
        theatre.setTheatreLocation(theatreDTO.getTheatreLocation());
        theatre.setTheatreCapacity(theatreDTO.getTheatreName());
       theatre.setTheatreScreenTye(theatreDTO.getTheatreScreenType());

        return theatreRepository.save(theatre);
    }
    public List<Theatre> getTheatreByLocation(String location){
        Optional<List<Theatre>>listOfTheatreBox=theatreRepository.findByLocation(location);
        if(listOfTheatreBox.isPresent()){
            return listOfTheatreBox.get();
        }
        else throw new RuntimeException("No theatres found for the location entered"+location);
    }
    public Theatre updateTheatre(Long id,TheatreDTO theatreDTO){
        Theatre theatre=theatreRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No Theatre found by id"+id));

        theatre.setTheatreName(theatreDTO.getTheatreName());
        theatre.setTheatreLocation(theatreDTO.getTheatreLocation());
        theatre.setTheatreCapacity(String.valueOf(theatreDTO.getTheatreCapacity()));
        theatre.setTheatreScreenTye(theatreDTO.getTheatreScreenType());

        return  theatreRepository.save(theatre);
    }
    public void deleteTheatre(Long id){
        theatreRepository.deleteById(id);
    }
}

