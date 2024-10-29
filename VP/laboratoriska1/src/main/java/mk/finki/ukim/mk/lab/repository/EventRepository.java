package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.SavedBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    private List<SavedBooking> savedBookings = new ArrayList<>();

    public List<Event> findAll() {
        return DataHolder.eventList;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.eventList.stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase()) ||
                                 event.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<SavedBooking> getSavedBookings() {
        return savedBookings;
    }

    public void addBooking(String evName, int numTickets) {
        boolean bookinExists = false;
        for (SavedBooking booking : savedBookings) {
            if (booking.getEventName().equals(evName)) {
                booking.setNumberOfTickets(booking.getNumberOfTickets() + numTickets);
                bookinExists = true;
                break;
            }
        }

        if (!bookinExists) {
            savedBookings.add(new SavedBooking(evName, numTickets));
        }
    }
}
