package br.com.dev.api.pass.in.repository;

import br.com.dev.api.pass.in.model.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
    Integer countByEventId(String eventId);

    List<Attendee> findByEventId(String eventId);

    Boolean existsByEventIdAndEmail(String eventId, String email);
}
