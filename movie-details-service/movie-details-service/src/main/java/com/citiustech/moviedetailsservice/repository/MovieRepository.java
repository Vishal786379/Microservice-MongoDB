package com.citiustech.moviedetailsservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.citiustech.moviedetailsservice.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, Integer> {

}
