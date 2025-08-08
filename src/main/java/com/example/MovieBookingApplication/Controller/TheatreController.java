package com.example.MovieBookingApplication.Controller;

import com.example.MovieBookingApplication.DTO.TheatreDTO;
import com.example.MovieBookingApplication.Service.TheatreService;
import com.example.MovieBookingApplication.entity.Theatre;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping("/addtheatre")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> addTheatre(@RequestBody TheatreDTO theatreDTO){
        return ResponseEntity.ok(theatreService.addTheatre(theatreDTO));
    }

    @GetMapping("/gettheatrebyocation")
    public ResponseEntity<List<Theatre>> getTheatreByLocation(@RequestParam String location){
        return ResponseEntity.ok(theatreService.getTheatreByLocation(location));
    }

    @PutMapping("/updatetheatre")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre>updateTheatre(@PathVariable Long id,@RequestBody TheatreDTO theatreDTO){
        return ResponseEntity.ok(theatreService.updateTheatre(id,theatreDTO));
    }

    @DeleteMapping("/deletetheatre/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id){
        theatreService.deleteTheatre(id);

        return ResponseEntity.ok().build();
    }



}
