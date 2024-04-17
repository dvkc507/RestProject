package restUtil;

public class Payload {
	public static String getcreatePlacePayload() {
		 return"{\r\n"
		 		+ "  \"location\": {\r\n"
		 		+ "    \"lat\": -38.383494,\r\n"
		 		+ "    \"lng\": 33.427362\r\n"
		 		+ "  },\r\n"
		 		+ "  \"accuracy\": 50,\r\n"
		 		+ "  \"name\": \"Frontline house\",\r\n"
		 		+ "  \"phone_number\": \"(+91) 923 893 3937\",\r\n"
		 		+ "  \"address\": \"29, hyserabad, Raheja 12B\",\r\n"
		 		+ "  \"types\": [\r\n"
		 		+ "    \"prolifics park\",\r\n"
		 		+ "    \"14 floor\"\r\n"
		 		+ "  ],\r\n"
		 		+ "  \"website\": \"http://google.com\",\r\n"
		 		+ "  \"language\": \"French-IN\"\r\n"
		 		+ "}\r\n"
		 		+ "";
	}
	
	public static String getUpdatePlacePayload(String placeID) {
		return "{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\"70 Summer walk, India\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
	}
	public static String getDeletePlacePayload(String placeID) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}";
	}
}
