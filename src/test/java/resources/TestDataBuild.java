package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
 public  AddPlace AddPlacePayload(String name, String address, String language) {
	 
	 AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setWebsite("http://google.com");
		ap.setPhone_number("(+91) 983 893 3937");
		
		
		List<String> l = new ArrayList<String>();
		l.add("shoe park");
		l.add("shop");
		
		ap.setTypes(l);
		
		Location lo = new Location();
		lo.setLat(-38.383494);
		lo.setLng(33.427362);
		
		ap.setLocation(lo);
		
		return ap;
 }

 public String DeletePlacePayload(String place) {
	 
	 return "{\r\n"
	 		+ "    \"place_id\":\""+place+"\""
	 		+ "}";
 }
}
