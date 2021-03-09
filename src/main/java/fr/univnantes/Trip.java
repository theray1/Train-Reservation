package fr.univnantes;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * Represents a trip between two cities, using a specific train and at a specific time.
 */
public interface Trip {

    /**
     * Retrieves the origin city of the trip.
     * @return the origin city of the trip.
     */
    City getOrigin();

    /**
     * Retrieves the destination city of the trip.
     * @return the destination city of the trip.
     */
    City getDestination();

    /**
     * Retrieves the train used for the trip.
     * @return the train used for the trip.
     */
    Train getTrain();

    /**
     * Finds the duration of the trip.
     * @return The duration of the trip (ie. the duration between departure and arrival).
     */
    Duration findDuration();

    /**
     * Retrieves whether the trip was cancelled or not.
     * @return true if the trip was cancelled, false otherwise.
     */
    boolean isCancelled();

    /**
     * Retrieves whether the trip was delayed or not.
     * @return true if the trip was delayed (ie. if the amount of delay it above zero), false otherwise.
     */
    boolean isDelayed();

    /**
     * Cancels the trip.
     * This also automatically cancels all tickets for this trip.
      */
    void cancel();

    /**
     * Get the initially planned departure time.
     * @return the initially planned departure time.
     */
    Instant getPlannedDepartureTime();

    /**
     * Get the initially planned arrival time.
     * @return the initially planned arrival time.
     */
    Instant getPlannedArrivalTime();

    /**
     * Finds the real departure time, considering delays.
     * @return the real departure time, considering delays.
     */
    Instant findRealDepartureTime();

    /**
     * Finds the real arrival time, considering delays.
     * @return the real arrival time, considering delays.
     */
    Instant findRealArrivalTime();

    /**
     * Retrieves the departure delay for this trip.
     * @return the departure delay.
     */
    Duration getDepartureDelay();

    /**
     * Retrieves the arrival delay for this trip.
     * @return the arrival delay.
     */
    Duration getArrivalDelay();

    /**
     * Retrieves the list of all (non-cancelled) booked tickets for this trip.
     * @return the list of all (non-cancelled) booked tickets for this trip.
     */
    List<Ticket> getBookedTickets();

    /**
     * Retrieves the list of all cancelled tickets for this trip.
     * @return the list of all cancelled tickets for this trip.
     */
    List<Ticket> getCancelledTickets();
}
