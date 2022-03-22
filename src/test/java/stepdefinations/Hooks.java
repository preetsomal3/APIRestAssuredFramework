package stepdefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		
		PlaceValidations p = new PlaceValidations();
		
		if(PlaceValidations.placeid==null) {
			
			p.add_place_payload_with_name_address_language("somal", "34 west", "Hindi");
			p.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		    p.verify_place_id_created_maps_to_with("somal", "getPlaceAPI");
			
			
		}
		
		
	}

}
