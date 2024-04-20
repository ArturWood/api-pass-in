package br.com.dev.api.pass.in.repository;

import br.com.dev.api.pass.in.model.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
}
