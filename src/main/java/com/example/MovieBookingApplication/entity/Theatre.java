package com.example.MovieBookingApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theatreName;
    private String theatreLocation;
    private String theatreCapacity;
    private String theatreScreenTye;

    @OneToMany(mappedBy = "theatre",fetch = FetchType.LAZY)
    private List<Show> show;


}
