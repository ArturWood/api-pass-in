package br.com.dev.api.pass.in.model.event;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String details;
    @Column(nullable = false, unique = true)
    private String slug;
    @Column(nullable = false, name = "maximum_attendees")
    private Integer maximumAttendees;

    public Event() {
    }

    public Event(String title, String details, String slug, Integer maximumAttendees) {
        this.title = title;
        this.details = details;
        this.slug = slug;
        this.maximumAttendees = maximumAttendees;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getSlug() {
        return slug;
    }

    public Integer getMaximumAttendees() {
        return maximumAttendees;
    }
}
