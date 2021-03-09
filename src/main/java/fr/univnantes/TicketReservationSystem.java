package fr.univnantes;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents a ticket reservation system. It can be used both to manage
 * new trips, trains and cities, and to manage tickets from the customer side.
 * It is aware of all existing cities, trips, tickets, and trains.
 */
public interface TicketReservationSystem {

    /**
     * Creates a new Ticket for a given trip, and records the ticket in the trip.
     * It is only possible to book a ticket if the trip had not already reached the maximum amount of passengers.
     * @param trip The trip for which a ticket must be booked.
     * @param passengerName The name of the passenger.
     * @return The ticket that has been booked.
     * @throws ReservationException If the trip has already reached the maximum amount of passengers.
     */
    Ticket bookTicket(Trip trip, String passengerName) throws ReservationException;

    /**
     * Exchanges a ticket for a new ticket for a different trip.
     * Once exchanged, a ticket becomes cancelled.
     * It is only possible to exchange a ticket if the new trip has the same origin and the same destination,
     * and if the new trip has not been cancelled, and if the new trip departure is after the ticket departure.
     * @param ticket The ticket to exchange.
     * @param trip The trip for the new ticket.
     * @throws ReservationException If the trip does not satisfy the constraints.
     * @return The new ticket
     */
    Ticket exchangeTicket(Ticket ticket, Trip trip) throws ReservationException;

    /**
     * Retrieves the list of all booked tickets (not cancelled).
     * @return The list of all booked tickets (not cancelled).
     */
    List<Ticket> getAllBookedTickets();

    /**
     * Retrieves the list of all cancelled tickets.
     * @return The list of all cancelled tickets.
     */
    List<Ticket> getAllCancelledTickets();

    /**
     * Finds all the trips eligible for an exchange against a ticket.
     * A trip is eligible for an exchange if the trip has the same origin and the same destination as the ticket,
     * and if the trip has not been cancelled, and if the trip departure is after the ticket departure.
     * @param ticket The ticket for which to find exchange possibilities.
     * @return The list of all trips eligible for the exchange.
     */
    List<Trip> findPossibleExchanges(Ticket ticket);

    /**
     * Finds all the available trips (not cancelled) originating from a City.
     * @param origin The city from which trips should originate.
     * @return All non-cancelled trips matching the criteria.
     */
    List<Trip> findAvailableTrips(City origin, LocalDate date);

    /**
     * Finds all the available trips (not cancelled) originating from a City, and arriving in a specific city, at a specific date.
     * @param origin The city from which trips should originate.
     * @param destination The city to which trips should arrive.
     * @param date The date of the trip.
     * @return All non-cancelled trips matching the criteria.
     */
    List<Trip> findAvailableTrips(City origin, City destination, LocalDate date);

    /**
     * Retrieves the list of all cities registered in the system.
     * @return The list of all cities registered in the system.
     */
    List<City> getCities();

    /**
     * Retrieves the list of all trains registered in the system.
     * @return The list of all trains registered in the system.
     */
    List<Train> getAllTrains();

    /**
     * Retrieves the list of all non-cancelled trips registered in the system.
     * @return The list of all non-cancelled trips registered in the system.
     */
    List<Trip> getAllTrips();

    /**
     * Retrieves the list of all cancelled trips registered in the system.
     * @return The list of all cancelled trips registered in the system.
     */
    List<Trip> getAllCancelledTrips();

    /**
     * Adds a new city in the system.
     * @param city The city to add.
     */
    void addCity(City city);

    /**
     * Creates and registers a new trip in the system.
     * A trip can only be created if the following constraints are satisfied:
     * - The train must not be already in another trip at the same time as the new trip.
     * - The last destination of the train must be in the same city as the origin of the new trip.
     * - The last arrival of the train must be at least 10 minutes from the departure of the new trip.
     * - The arrival must come after the departure.
     * - The origin must be different from the destination.
     * @return The created registered trip.
     * @throws TripException If one of the above constraints was not satisfied.
     */
    Trip createTrip(City origin, City destination, Train train, Instant departure, Instant arrival) throws TripException;

    /**
     * Cancels a trip.
     * @param trip The trip to cancel.
     */
    void cancelTrip(Trip trip);


    /**
     * Adds a departure delay to a trip.
     * Also automatically adds an arrival delay of the same amount.
     * @param delay The amount of delay to add.
     */
    void addDepartureDelay(Trip trip, Duration delay);

    /**
     * Adds an arrival delay to a trip.
     * If needed, this automatically also adds delays to the next trip of the train, so that the next train
     * departure remains at least 10 minutes from the arrival time of the delayed trip.
     * @param delay The amount of delay to add.
     */
    void addArrivalDelay(Trip trip, Duration delay);
}

