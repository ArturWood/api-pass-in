package br.com.dev.api.pass.in.repository;

import br.com.dev.api.pass.in.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
