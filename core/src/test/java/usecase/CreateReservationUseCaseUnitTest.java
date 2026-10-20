package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import reserva.exception.PersistenceException;
import reserva.exception.ReservationAlreadyExistsException;
import reserva.modelo.Reserva;
import reserva.output.ReservationRepository;
import reserva.usecase.CreateReservationUseCase;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateReservationUseCaseUnitTest {

    @Mock
    private ReservationRepository reservationRepository;

        /*Reserva reservation = Reserva.instance(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                "test@email.com",
                LocalDate.of(2026, 10, 20),
                4,
                "ACTIVA"
        );
*/
    @Test
    void register_CreateReservation_Success(){
        // Arrange
        CreateReservationUseCase createReservationUseCase = new CreateReservationUseCase(reservationRepository);
        when(reservationRepository.existe("test@email.com",LocalDate.of(2026,10,25)))
                .thenReturn(false);
        when(reservationRepository.guardarReserva(org.mockito.ArgumentMatchers.any(Reserva.class)))
                .thenReturn(true);

        // Act
        Reserva result = createReservationUseCase.registrar(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                "test@email.com",
                LocalDate.of(2026, 10, 20),
                4,
                "ACTIVA"
        );

        // Assert
        Assertions.assertNotNull(result);
    }
    @Test
    void register_CreateReservation_AlreadyExists(){
        // Arrange
        CreateReservationUseCase createReservationUseCase = new CreateReservationUseCase(reservationRepository);
        when(reservationRepository.existe("test@email.com",LocalDate.of(2026,10,25)))
                .thenReturn(true);
        when(reservationRepository.guardarReserva(org.mockito.ArgumentMatchers.any(Reserva.class)))
                .thenReturn(true);

        // Act y Assert
        Assertions.assertThrows(ReservationAlreadyExistsException.class,
                ()-> createReservationUseCase.registrar(
                        UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                    "test@email.com",
                    LocalDate.of(2026, 10, 20),
                    4,
                    "ACTIVA"
                )
        );

    }
    @Test
    void register_CreateReservation_NotSaved(){
        // Arrange
        CreateReservationUseCase createReservationUseCase = new CreateReservationUseCase(reservationRepository);
        when(reservationRepository.existe("test@email.com",LocalDate.of(2026,10,25)))
                .thenReturn(false);
        when(reservationRepository.guardarReserva(org.mockito.ArgumentMatchers.any(Reserva.class)))
                .thenReturn(false);

        // Act y Assert
        Assertions.assertThrows(PersistenceException.class,
                ()-> createReservationUseCase.registrar(
                    UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                    "test@email.com",
                    LocalDate.of(2026, 10, 20),
                    4,
                    "ACTIVA"
                )
        );

    }
}