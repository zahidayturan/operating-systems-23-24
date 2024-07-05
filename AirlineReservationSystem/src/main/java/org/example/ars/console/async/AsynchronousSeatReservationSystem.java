package org.example.ars.console.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Seat {
    private boolean reserved;

    public Seat() {
        this.reserved = false;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}

class SeatReservationSystem {
    private final List<Seat> seats = new ArrayList<>();

    public SeatReservationSystem(int numSeats) {
        for (int i = 0; i < numSeats; i++) {
            seats.add(new Seat());
        }
    }

    public void makeReservation(int seatIndex, String user) {
        Seat seat = seats.get(seatIndex);
        if (!seat.isReserved()) {
            seat.setReserved(true);
            System.out.println(user + " reserved seat " + seatIndex);
        } else {
            System.out.println(user + " tried to reserve seat " + seatIndex + " but it is already reserved");
        }
    }

    public void cancelReservation(int seatIndex, String user) {
        Seat seat = seats.get(seatIndex);
        if (seat.isReserved()) {
            seat.setReserved(false);
            System.out.println(user + " canceled reservation for seat " + seatIndex);
        } else {
            System.out.println(user + " tried to cancel reservation for seat " + seatIndex + " but it is not reserved");
        }
    }

    public void queryReservation(int seatIndex, String user) {
        Seat seat = seats.get(seatIndex);
        System.out.println(user + " checked seat " + seatIndex + ", reserved: " + seat.isReserved());
    }
}

public class AsynchronousSeatReservationSystem {

    public static void main(String[] args) {
        SeatReservationSystem reservationSystem = new SeatReservationSystem(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            int seatIndex = i;
            String user = "user" + (i + 1);
            executorService.submit(() -> reservationSystem.makeReservation(seatIndex, user));
            executorService.submit(() -> reservationSystem.cancelReservation(seatIndex, user));
            executorService.submit(() -> reservationSystem.queryReservation(seatIndex, user));
        }

        executorService.shutdown();
    }
}
