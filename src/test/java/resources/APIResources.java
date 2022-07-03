package resources;

/*
 * This is a an Enum Class
 * Enum class is special class in JAVA which has collection of constants or methods
 */

public enum APIResources {
	
	/*
	 * Enum class Methods
	 */
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	/*
	 * Enum class variables
	 */
	private String resource;
	
	//Constructor
	private APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
}
