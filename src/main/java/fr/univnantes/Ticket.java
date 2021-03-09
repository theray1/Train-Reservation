package fr.univnantes;

/**
 * Represents a ticket booked for a trip by a customer.
 * A ticket is nominative, and is only for one specific trip.
 */
public interface Ticket {

    /**
     * Retrieves the status of the trip (cancelled or not).
     * @return True of the trip was cancelled, false otherwise.
     */
    boolean isCancelled();

    /**
     * Retrieves the name if the passenger.
     * @return The name of the passenger.
     */
    String getPassengerName();

    /**
     * Retrieves the trip that this ticket was booked for.
     * @return the trip that this ticket was booked for.
     */
    Trip getTrip();

    /**
     * Cancels this ticket. Cannot be undone.
     * Once a ticket has been cancelled, isCancelled() always returns true.
     */
    void cancel();
}
