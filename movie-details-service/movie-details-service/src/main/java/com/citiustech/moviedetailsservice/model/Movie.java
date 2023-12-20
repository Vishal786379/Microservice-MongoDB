package com.citiustech.moviedetailsservice.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
	
	private int id;
	private String title;
	private int releaseYear;
	private List<String> directors;
	private List<String> casts;

}
