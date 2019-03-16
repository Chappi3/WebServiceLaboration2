package se.iths.tonny.webservicelaboration2;

import org.springframework.web.client.RestTemplate;
import se.iths.tonny.webservicelaboration2.model.Whiskey;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

public class Webservicelaboration2RestTestClient {

    public static final String REST_SERVICE_URI = "http://localhost:9000/api";

    // GET
    @SuppressWarnings("unchecked")
    private static void listAllWhiskeys() {
        System.out.println("---------- Testing listAllWhiskies API ----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> whiskiesMap = restTemplate.getForObject(REST_SERVICE_URI+"/whiskies/", List.class);

        if (whiskiesMap != null) {
            for (LinkedHashMap<String, Object> map : whiskiesMap) {
                System.out.println("Whiskey : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));
            }
        }
        else {
            System.out.println("---------- No whiskies exist ----------");
        }
    }

    // GET
    private static void getWhiskey() {
        System.out.println("---------- Testing getWhiskey API ----------");
        RestTemplate restTemplate = new RestTemplate();
        Whiskey whiskey = restTemplate.getForObject(REST_SERVICE_URI+"/whiskey/1", Whiskey.class);
        System.out.println(whiskey);
    }

    // POST
    private static void createWhiskey() {
        System.out.println("---------- Testing createWhiskey API ----------");
        RestTemplate restTemplate = new RestTemplate();
        Whiskey whiskey = new Whiskey(0, "Deveron", 12, 745.33);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/whiskey/", whiskey, Whiskey.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    // PUT
    private static void updateWhiskey() {
        System.out.println("---------- Testing updateWhiskey API ----------");
        RestTemplate restTemplate = new RestTemplate();
        Whiskey whiskey = new Whiskey(1, "Mackmyra", 12, 575.57);
        restTemplate.put(REST_SERVICE_URI+"/whiskey/1", whiskey);
        System.out.println(whiskey);
    }

    // DELETE
    private static void deleteWhiskey() {
        System.out.println("---------- Testing deleteWhiskey API ----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/whiskey/3");
    }

    // DELETE
    private static void deleteAllWhiskies() {
        System.out.println("---------- Testing deleteAllWhiskies API ----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/whiskies/");
    }

    public static void main(String[] args) {
        listAllWhiskeys();
        getWhiskey();
        createWhiskey();
        listAllWhiskeys();
        updateWhiskey();
        listAllWhiskeys();
        deleteWhiskey();
        listAllWhiskeys();
        deleteAllWhiskies();
        listAllWhiskeys();
    }
}
