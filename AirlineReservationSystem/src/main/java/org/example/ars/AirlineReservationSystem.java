package org.example.ars;

import org.example.ars.models.Seat;
import org.example.ars.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirlineReservationSystem implements CommandLineRunner {

    @Autowired
    private SeatRepository seatRepository;

    public static void main(String[] args) {
        SpringApplication.run(AirlineReservationSystem.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Starting with 10 seats
        for (int i = 1; i <= 10; i++) {
            Seat seat = new Seat();
            seat.setReserved(false);
            seatRepository.save(seat);
        }
    }
}
