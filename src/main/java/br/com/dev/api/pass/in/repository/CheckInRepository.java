package br.com.dev.api.pass.in.repository;

import br.com.dev.api.pass.in.model.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
}
