package reserva.output;

import reserva.modelo.Reserva;
import java.time.LocalDate;

public interface ReservationRepository {
    boolean existe(String mail, LocalDate fecha);
    boolean guardarReserva(Reserva reserva);
}
