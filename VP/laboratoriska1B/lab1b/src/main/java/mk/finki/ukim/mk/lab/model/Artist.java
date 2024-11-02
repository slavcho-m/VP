package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;

    public Artist(Long id, String firstName, String lastName, String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }
}
