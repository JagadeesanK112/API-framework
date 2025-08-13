package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Product {
private int id;
private String title;
private String category;
private String description;
private String image;
private int price;
private Rating rating;	
private double rate;	
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public static class Rating {
	private double rate;
	private int count;

	
}
}