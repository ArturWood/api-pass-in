package br.com.dev.api.pass.in.repository;

import br.com.dev.api.pass.in.model.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
    Optional<CheckIn> findByAttendeeId(String attendeeId);

    Boolean existsByAttendeeId(String attendeeId);
}
