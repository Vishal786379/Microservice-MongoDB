package com.citiustech.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.citiustech.moviecatalogservice.model.CatalogItem;
import com.citiustech.moviecatalogservice.model.Movie;
import com.citiustech.moviecatalogservice.model.MovieCatalog;
import com.citiustech.moviecatalogservice.model.MovieRating;
import com.citiustech.moviecatalogservice.model.Rating;

@RestController
@RequestMapping("/api/v1")
public class MovieCatalogController {
	
	@Value("${app.version}")
	private String appVersion;
	
	@Value("${app.name}")
	private String appName;
	
	@Value("${app.author.email}")
	private String appAuthorEmail;

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/catalog/{username}")
	public ResponseEntity<MovieCatalog> getMovieCatalogByUsername(@PathVariable String username){
		//consuming movie rating
		ResponseEntity<MovieRating> response = restTemplate.getForEntity("http://MOVIE-RATINGS-SERVICE/api/v1/ratings/" + username, MovieRating.class);
		MovieRating movieRating = response.getBody();
		List<Rating> ratings = movieRating.getRatings();
		
		//consuming Movie Details service
		MovieCatalog movieCatalog = new MovieCatalog();
		movieCatalog.setUsername(username);
		
		List<CatalogItem> catalogItems = new ArrayList<>();
		movieCatalog.setCatalogItems(catalogItems);
		
		for (Rating rating : ratings) {
			Movie movie = restTemplate.getForEntity("http://MOVIE-DETAILS-SERVICE/api/v1/movies/" + rating.getMovieId(), Movie.class).getBody();
			CatalogItem catalogItem = new CatalogItem(movie,rating.getRating());
			catalogItems.add(catalogItem);
		}
		return new ResponseEntity<MovieCatalog>(movieCatalog, HttpStatus.OK);
	}
	
	@GetMapping("/app-info")
	public String getAppDetails(){
		return String.format("App Version => %s | App Name => %s | App Author => %s%n", appVersion,appName,appAuthorEmail);
	}
	
	
	
}
