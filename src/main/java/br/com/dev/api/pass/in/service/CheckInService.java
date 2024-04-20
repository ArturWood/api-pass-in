package br.com.dev.api.pass.in.service;

import br.com.dev.api.pass.in.infra.exception.AttendeeAlreadyCheckedInException;
import br.com.dev.api.pass.in.infra.exception.CheckInNotFoundException;
import br.com.dev.api.pass.in.model.attendee.Attendee;
import br.com.dev.api.pass.in.model.checkin.CheckIn;
import br.com.dev.api.pass.in.repository.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckInRepository checkInRepository;

    public void registerCheckIn(Attendee attendee) {
        this.verifyCheckInExists(attendee.getId());
        CheckIn newCheckIn = new CheckIn(attendee);
        checkInRepository.save(newCheckIn);
    }

    private void verifyCheckInExists(String attendeeId) {
        Boolean exists = checkInRepository.existsByAttendeeId(attendeeId);
        if (exists) {
            throw new AttendeeAlreadyCheckedInException("O participante " + attendeeId + " ja realizou o check in");
        }
    }

    public CheckIn getCheckIn(String attendeeId) {
        return checkInRepository.findByAttendeeId(attendeeId).orElseThrow(() -> new CheckInNotFoundException("Check In não encontrado para Attendee Id: " + attendeeId));
    }
}
