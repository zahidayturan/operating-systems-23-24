package org.example.ars.service;

import org.example.ars.models.Seat;
import org.example.ars.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    private final Lock lock = new ReentrantLock();


    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Transactional
    public Seat reserveSeat(Long id, String userId) {
        lock.lock();
        try {
            Optional<Seat> seatOptional = seatRepository.findById(id);
            if (seatOptional.isPresent()) {
                Seat seat = seatOptional.get();
                if (!seat.isReserved()) {
                    seat.setReserved(true);
                    seat.setReservedBy(userId);
                    System.out.println("Seat " + id + " reserved by " + userId);
                    return seatRepository.save(seat);
                } else {
                    throw new RuntimeException("Seat is already reserved");
                }
            } else {
                throw new RuntimeException("Seat not found");
            }
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public Seat cancelReservation(Long id, String userId) {
        lock.lock();
        try {
            Optional<Seat> seatOptional = seatRepository.findById(id);
            if (seatOptional.isPresent()) {
                Seat seat = seatOptional.get();
                if (seat.isReserved() && seat.getReservedBy().equals(userId)) {
                    seat.setReserved(false);
                    seat.setReservedBy(null);
                    System.out.println("Reservation of seat " + id + " by " + userId + " cancelled");
                    return seatRepository.save(seat);
                } else if (!seat.isReserved()) {
                    throw new RuntimeException("Seat is not reserved");
                } else {
                    throw new RuntimeException("Seat is reserved by another user");
                }
            } else {
                throw new RuntimeException("Seat not found");
            }
        } finally {
            lock.unlock();
        }
    }
}
