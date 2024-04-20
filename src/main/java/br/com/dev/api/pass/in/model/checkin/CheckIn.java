package br.com.dev.api.pass.in.model.checkin;

import br.com.dev.api.pass.in.model.attendee.Attendee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_ins")
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @OneToOne
    @JoinColumn(name = "attendee_id", nullable = false)
    private Attendee attendee;

    public CheckIn() {
    }

    public CheckIn(Attendee attendee) {
        this.attendee = attendee;
        this.createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Attendee getAttendee() {
        return attendee;
    }
}
