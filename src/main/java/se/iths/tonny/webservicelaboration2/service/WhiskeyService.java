package se.iths.tonny.webservicelaboration2.service;

import se.iths.tonny.webservicelaboration2.model.Whiskey;

import java.util.List;

public interface WhiskeyService {

    Whiskey findById(long id);
    Whiskey findByName(String name);

    void saveWhiskey(Whiskey whiskey);
    void updateWhiskey(Whiskey whiskey);
    void deleteWhiskeyById(long id);
    void deleteAllWhiskies();

    boolean isWhiskeyExist(Whiskey whiskey);

    List<Whiskey> findAllWhiskies();
}
