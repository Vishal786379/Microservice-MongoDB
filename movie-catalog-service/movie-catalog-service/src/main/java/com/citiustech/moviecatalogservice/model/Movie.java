package com.citiustech.moviecatalogservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
