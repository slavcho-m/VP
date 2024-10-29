package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> eventList = new ArrayList<>();

    @PostConstruct
    public void init() {
        eventList.add(new Event("Gamers Cup", "A finale where gamers compete for the title of BEST", 10.00));
        eventList.add(new Event("Cooking Show", "The best chefs show you how its done", 7.30));
        eventList.add(new Event("Basketball Finale", "The 2 best teams will settle their score in a 3v3 showoff", 10.00));
        eventList.add(new Event("CS Meetup", "Biggest meetup for the students of FINKI", 9.25));
        eventList.add(new Event("Bullet Chess Finale", "Fastest chess players will challange the title of GM", 6.75));
        eventList.add(new Event("National Volleyball Championship", "The brackets will face off in the start of the national championship", 7.45));
        eventList.add(new Event("Test", "This is a test example with description", 2.15));
        eventList.add(new Event("Test Number 2", "This is the second test", 1.45));
        eventList.add(new Event("Test Finale", "This is the final test of the test section", 1.00));
        eventList.add(new Event("Code Wars", "A hackaton for the fastest programmers", 10.00));
    }
}
