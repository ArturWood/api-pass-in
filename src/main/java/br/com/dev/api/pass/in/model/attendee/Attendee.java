package br.com.dev.api.pass.in.model.attendee;

import br.com.dev.api.pass.in.model.event.Event;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendees")
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Attendee() {
    }

    public Attendee(String name, String email, Event event) {
        this.name = name;
        this.email = email;
        this.event = event;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Event getEvent() {
        return event;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
