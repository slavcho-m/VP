package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double popularityScore;
    private boolean isRatingManipulated;

    @ManyToOne
    private Location location;

    // Constructor

    public Event(String name, String description, double popularityScore, Location location) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
        this.isRatingManipulated = false;
    }

    public void setRatingManipulated() {
        this.isRatingManipulated = true;
    }

    public boolean isRatingManipulated() {
        return isRatingManipulated;
    }

    public void changePopularity(String type) {
        if (type.equals("increment")) {
            this.popularityScore += 1;
        } else if (type.equals("decrease")) {
            this.popularityScore -= 1;
        }
    }
}
