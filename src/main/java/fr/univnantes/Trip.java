package fr.univnantes;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public interface Trip {

    City getOrigin();
    City getDestination();
    Train getTrain();
    Duration getDuration();
    boolean isCancelled();
    boolean isDelayed();
    void cancel();
    Instant getPlannedDepartureTime();
    Instant getPlannedArrivalTime();
    Instant getRealDepartureTime();
    Instant getRealArrivalTime();
    void addDepartureDelay(Duration delay);
    void addArrivalDelay(Duration delay);
    void getDepartureDelay(Duration delay);
    void getArrivalDelay(Duration delay);
    List<Ticket> getBookedTickets();
    List<Ticket> getCancelledTickets();
}
