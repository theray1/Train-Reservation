import fr.univnantes.trainreservation.City;
import fr.univnantes.trainreservation.Ticket;
import fr.univnantes.trainreservation.Train;
import fr.univnantes.trainreservation.Trip;
import fr.univnantes.trainreservation.impl.*;
import fr.univnantes.trainreservation.util.TimeManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TicketReservationSystemImplTest {

    private TicketReservationSystemImpl trs;

    @BeforeEach
    void beforeEach(){
        trs = new TicketReservationSystemImpl(ZoneId.systemDefault());
    }


    @Test
    void getAllBookedTicketsIsInitiallyEmpty(){
        List<Ticket> testList = trs.getAllBookedTickets();

        assertTrue(testList.isEmpty());
    }

    @Test
    void getAllBookedTicketsContainsAllAddedTickets(){
        List<Ticket> testList = trs.getAllBookedTickets();

        City city1 = new CityImpl("Nantes");
        City city2 = new CityImpl("Nancy");
        Train train = new TrainImpl("Nantes-Nancy", 1000);
        Instant departure = TimeManagement.createInstant("2021-03-11 11:00", ZoneId.systemDefault());
        Instant ending = TimeManagement.createInstant("2021-03-11 15:00", ZoneId.systemDefault());

        Trip trip = new TripImpl(city1, city2, train, departure, ending);

        Ticket t1 = new TicketImpl("Luma", trip);

        testList.add(t1);

        ArrayList<Ticket> testedTickets = new ArrayList<>();
        testedTickets.add(t1);

        assertTrue(testList.containsAll(testedTickets));
    }
}
