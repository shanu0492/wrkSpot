package com.api.model;


import lombok.Data;

@Data
public class CountriesResponse {
	private String abbreviation;
	private String capital;
	private String currency;
	private String name;
	private String phone;
	private Integer population;
	private Media media;
	private Integer id;

}
