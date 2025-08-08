package com.example.MovieBookingApplication.Controller;


import com.example.MovieBookingApplication.DTO.ShowDTO;
import com.example.MovieBookingApplication.Service.ShowService;
import com.example.MovieBookingApplication.entity.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/createshow")
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.createShow(showDTO));
    }
    @GetMapping("/getallshows")
    public ResponseEntity<List<Show>> getAllShows(){
        return ResponseEntity.ok(showService.getAllShows());
    }
    @GetMapping("/getshowsbymovie")
    public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowsByMovie(id));
    }
    @GetMapping("/getshowsbytheatre")
    public ResponseEntity<List<Show>> getShowsByTheatre(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowsByTheatre(id));
    }
    @PutMapping("/updateshow/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id,@RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.updateShow(id,showDTO));
    }
    @DeleteMapping("/deleteshow/{id}")
    public ResponseEntity<Void>deleteShow(@PathVariable Long id){
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }
}
