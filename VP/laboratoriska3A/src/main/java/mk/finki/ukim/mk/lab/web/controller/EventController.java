package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.LocationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/events/events-list")
public class EventController {
    private final EventServiceImpl eventService;
    private final LocationServiceImpl locationService;

    public EventController(EventServiceImpl eventService, LocationServiceImpl locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    // TODO: need exceptions (prob next lab)
    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest req) {
        // Fetch data
        List<Location> locations = locationService.listAll();
        if (locations == null) {
            locations = List.of();
        }
        List<Event> eventList = eventService.listAll();
        if (eventList == null) {
            eventList = List.of();
        }

        String searchName = req.getParameter("searchName");
        String minRating = req.getParameter("minRating");

        // Filter logic
        if (searchName != null && minRating != null && !minRating.isEmpty()) {
            eventList = eventService.searchEvents(searchName).stream()
                    .filter(event -> event.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        } else if (minRating != null && !minRating.isEmpty()) {
            eventList = eventService.listAll().stream()
                    .filter(event -> event.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        } else if (searchName != null) {
            eventList = eventService.searchEvents(searchName);
        } else {
            eventList = eventService.listAll();
        }

        // Check if there are no events or locations
        if (eventList.isEmpty()) {
            model.addAttribute("events", null);
        } else {
            model.addAttribute("events", eventList);
        }

        if (locations.isEmpty()) {
            model.addAttribute("locations", null);
        } else {
            model.addAttribute("locations", locations);
        }

        return "listEvents";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/events/events-list";
    }

    @GetMapping("/add-form")
    public String addEventPage(Model model) {
        List<Location> locationList = locationService.listAll();
        if (locationList == null) {
            locationList = List.of();
        }
        model.addAttribute("locations", locationList);
        model.addAttribute("event", new Event());
        return "add-event";
    }

    @GetMapping("/edit-form/{id}")
    public String editEventPage(Model model, @PathVariable Long id) {
        Event event = eventService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
        List<Location> locationList = locationService.listAll();
        if (locationList == null) {
            locationList = List.of();
        }
        model.addAttribute("locations", locationList);
        model.addAttribute("event", event);
        return "add-event";
    }

    @GetMapping("/event-details/{id}")
    public String eventDetailsPage(Model model, @PathVariable Long id) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            model.addAttribute("event", event);
            return "event-details";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @GetMapping("/by-location")
    public String getEventsByLocation(@RequestParam Long locationId, Model model) {
        List<Event> events = eventService.findAllByLocationId(locationId);
        model.addAttribute("events", events);
        model.addAttribute("locations", locationService.listAll());
        return "listEvents";
    }

    @PostMapping("/change-rating/{id}")
    public String changeEventRating(@PathVariable Long id, HttpServletRequest req) {
        if (this.eventService.findById(id).isPresent()) {
            if (req.getParameter("increment") != null) {
                this.eventService.changeEventRating(id, req.getParameter("increment"));
            } else if (req.getParameter("decrease") != null) {
                this.eventService.changeEventRating(id, req.getParameter("decrease"));
            }
            return "redirect:/events/events-list/event-details/" + id;
        }
        return "redirect:/events?error=EventNotFound";
    }

    @PostMapping("/add")
    public String saveEvent(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double popularityScore,
            @RequestParam Long location
    ) {
        if (id != null) {
            this.eventService.update(id, name, description, popularityScore, location);
        } else {
            this.eventService.save(name, description, popularityScore, location);
        }
        return "redirect:/events/events-list";
    }
}
