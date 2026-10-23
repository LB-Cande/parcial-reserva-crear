package reserva.usecase;

import reserva.exception.PersistenceException;
import reserva.exception.ReservationAlreadyExistsException;
import reserva.modelo.Reserva;
import reserva.output.ReservationRepository;

import java.time.LocalDate;
import java.util.UUID;

public class CreateReservationUseCase {

    private final ReservationRepository reservationRepository;

    public CreateReservationUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reserva registrar(
                             UUID id,
                             String mail,
                             LocalDate fecha,
                             int cantidadPersonas,
                             String estado) {

        Reserva newReserva = Reserva.instance(id,mail,fecha,cantidadPersonas,estado);
            if (reservationRepository.existe(mail,fecha)) {
            throw new ReservationAlreadyExistsException("Ya existe una reserva activa para esa fecha por: "+mail);
        }
        if (!reservationRepository.guardarReserva(newReserva)) {
            throw new PersistenceException("No se pudo guardar la reserva");
        }
        return newReserva;


    }

}

