package se.iths.tonny.webservicelaboration2.service;

import se.iths.tonny.webservicelaboration2.model.Whiskey;

import java.util.List;

public interface WhiskeyService {

    Whiskey findById(long id);
    Whiskey findByName(String name);

    void saveWhiskey();
    void updateWhiskey();
    void deleteWhiskeyById(long id);
    void deleteAllWhiskeys();

    boolean isWhiskeyExist(Whiskey whiskey);

    List<Whiskey> findAllWhiskeys();
}
