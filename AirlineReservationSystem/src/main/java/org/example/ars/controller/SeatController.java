package org.example.ars.controller;

import org.example.ars.models.Seat;
import org.example.ars.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @PostMapping("/{id}/reserve")
    public Seat reserveSeat(@PathVariable Long id, @RequestParam String userId) {
        return seatService.reserveSeat(id, userId);
    }

    @PostMapping("/{id}/cancel")
    public Seat cancelReservation(@PathVariable Long id, @RequestParam String userId) {
        return seatService.cancelReservation(id, userId);
    }
}
