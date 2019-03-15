package se.iths.tonny.webservicelaboration2.service;

import org.springframework.stereotype.Service;
import se.iths.tonny.webservicelaboration2.model.Whiskey;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("whiskeyService")
public class WhiskeyServiceImpl implements WhiskeyService {

    private static final AtomicLong counter = new AtomicLong();
    private static List<Whiskey> whiskies;

    @Override
    public Whiskey findById(long id) {
        for (Whiskey whiskey : whiskies) {
            if (whiskey.getId() == id) {
                return whiskey;
            }
        }
        return null;
    }

    @Override
    public Whiskey findByName(String name) {
        for (Whiskey whiskey : whiskies) {
            if (whiskey.getName().equalsIgnoreCase(name)) {
                return whiskey;
            }
        }
        return null;
    }

    @Override
    public void saveWhiskey(Whiskey whiskey) {
        whiskey.setId(counter.incrementAndGet());
        whiskies.add(whiskey);
    }

    @Override
    public void updateWhiskey(Whiskey whiskey) {
        int index = whiskies.indexOf(whiskey);
        whiskies.set(index, whiskey);
    }

    @Override
    public void deleteWhiskeyById(long id) {
        whiskies.removeIf(whiskey -> whiskey.getId() == id);
    }

    @Override
    public void deleteAllWhiskies() {
        whiskies.clear();
    }

    @Override
    public boolean isWhiskeyExist(Whiskey whiskey) {
        return findByName(whiskey.getName()) != null;
    }

    @Override
    public List<Whiskey> findAllWhiskies() {
        return whiskies;
    }

    private static List<Whiskey> populateDummyWhiskies() {
        List<Whiskey> whiskies = new ArrayList<>();
        whiskies.add(new Whiskey(counter.incrementAndGet(), "Lagavulin", 16, 628.11));
        whiskies.add(new Whiskey(counter.incrementAndGet(), "Dalmore", 15, 768.66));
        whiskies.add(new Whiskey(counter.incrementAndGet(), "Knockando", 18, 831.73));
        whiskies.add(new Whiskey(counter.incrementAndGet(), "Strathisla", 12, 418.70));
        return whiskies;
    }
}
