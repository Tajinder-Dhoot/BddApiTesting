package resources;

import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String address, String website) {
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite(website);
		p.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	public DeletePlace deletePlacePayload(String place_id) {
		
		DeletePlace deletePlace = new DeletePlace();
		deletePlace.setPlace_id(place_id);
		return deletePlace;
	}
	
//	public String deletePlacePayload(String placeId) {
//		return "{\r\n    \"place_id\" : \"" +placeId+ "\"\r\n}";
//	}
}
