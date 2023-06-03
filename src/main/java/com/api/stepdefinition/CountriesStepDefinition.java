package com.api.stepdefinition;

import static org.junit.Assert.*;

import com.api.model.CountriesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.api.utils.TestContext;

import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CountriesStepDefinition {

	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(CountriesStepDefinition.class);
	
	 public CountriesStepDefinition(TestContext context) {
		this.context = context;
	}

	@Given("user has access to endpoint {string}")
	public void userHasAccessToEndpoint(String endpoint) {		
		context.session.put("endpoint", endpoint);
	}

	@When("user hits the request")
	public void userCreatesABooking() {
		context.response = context.requestSetup().when().get(context.session.get("endpoint").toString());
	}


	@Then("user should get the response code {int}")
	public void userShouldGetTheResponseCode(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
	}

	@Then("validate the response bt deserializing")
	public void validate_the_response_bt_deserializing() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<CountriesResponse> responseDeserialized = mapper.readValue(context.response.asString(),new TypeReference<List<CountriesResponse>>() {});
		Set<String> abb = new HashSet<>();
		//Validation that no duplicate abbreviation is there
		responseDeserialized.stream().forEach(e->{
			if(abb.contains(e.getAbbreviation()))
				Assert.assertTrue(false);
			abb.add(e.getAbbreviation());
		});
	}

}
