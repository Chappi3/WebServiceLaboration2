package se.iths.tonny.webservicelaboration2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import se.iths.tonny.webservicelaboration2.model.Whiskey;
import se.iths.tonny.webservicelaboration2.service.WhiskeyService;
import se.iths.tonny.webservicelaboration2.util.CustomErrorType;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    private static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    WhiskeyService whiskeyService;

    // -------------------- Retrieve all whiskies --------------------

    @RequestMapping(value = "/whiskies/", method = RequestMethod.GET)
    public ResponseEntity<List<Whiskey>> listAllWhiskies() {
        List<Whiskey> whiskies = whiskeyService.findAllWhiskies();
        if (whiskies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(whiskies, HttpStatus.OK);
    }

    // -------------------- Retrieve a single whiskey --------------------

    @RequestMapping(value = "/whiskey/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWhiskey(@PathVariable("id") long id) {
        logger.info("Fetching Whiskey with id {}", id);
        Whiskey whiskey = whiskeyService.findById(id);
        if (whiskey == null) {
            logger.error("Whiskey with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Whiskey with id " + id
                    + " not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(whiskey, HttpStatus.OK);
    }

    // -------------------- Create a whiskey --------------------

    @RequestMapping(value = "/whiskey/", method = RequestMethod.POST)
    public ResponseEntity<?> createWhiskey(@RequestBody Whiskey whiskey, UriComponentsBuilder ucBuilder) {
        logger.info("Creating whiskey : {}", whiskey);

        if (whiskeyService.isWhiskeyExist(whiskey)) {
            logger.error("Unable to create. A whiskey with name {} already exist.", whiskey.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A whiskey with name " +
                    whiskey.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        whiskeyService.saveWhiskey(whiskey);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/whiskey/{id}").buildAndExpand(whiskey.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // -------------------- Update a whiskey --------------------

    @RequestMapping(value = "/whiskey/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateWhiskey(@PathVariable("id") long id, @RequestBody Whiskey whiskey) {
        logger.info("Updating whiskey with id {}", id);

        Whiskey currentWhiskey = whiskeyService.findById(id);

        if (currentWhiskey == null) {
            logger.error("Unable to update. Whiskey with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Whiskey with id " +
                    id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentWhiskey.setName(whiskey.getName());
        currentWhiskey.setAge(whiskey.getAge());
        currentWhiskey.setPrice(whiskey.getPrice());

        whiskeyService.updateWhiskey(currentWhiskey);
        return new ResponseEntity<>(currentWhiskey, HttpStatus.OK);
    }

    // -------------------- Delete a whiskey --------------------

    @RequestMapping(value = "/whiskey/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWhiskey(@PathVariable("id") long id) {
        logger.info("Fetching & deleting whiskey with id {}", id);

        Whiskey whiskey = whiskeyService.findById(id);
        if (whiskey == null) {
            logger.error("Unable to delete. Whiskey with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Whiskey with id " +
                    id + " not found."), HttpStatus.NOT_FOUND);
        }
        whiskeyService.deleteWhiskeyById(id);
        return new ResponseEntity<Whiskey>(HttpStatus.NO_CONTENT);
    }

    // -------------------- Delete all whiskies --------------------

    @RequestMapping(value = "/whiskies/", method = RequestMethod.DELETE)
    public ResponseEntity<Whiskey> deleteAllWhiskies() {
        logger.info("Deleting all whiskies");

        whiskeyService.deleteAllWhiskies();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
