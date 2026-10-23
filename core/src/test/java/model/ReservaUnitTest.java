package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reserva.exception.*;
import reserva.modelo.Reserva;

import java.time.LocalDate;
import java.util.UUID;

public class ReservaUnitTest {

    @Test
    public void InstanceReserva_EmptyID_EmptyFieldException(){
        Assertions.assertThrows(EmptyFieldException.class, ()-> Reserva.instance(
                null,
                "fulanitodetal@gmail.com",
                LocalDate.of(2026,10,25),
                4,
                "ACTIVA"
        ));
    }

    @Test
    public void InstanceReserva_EmptyDate_EmptyFieldException(){
        Assertions.assertThrows(EmptyFieldException.class, ()-> Reserva.instance(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                "fulanitodetal@gmail.com",
                null,
                4,
                "ACTIVA"
        ));
    }

    @Test
    public void InstanceReserva_EmptyMail_EmptyFieldException(){
        Assertions.assertThrows(EmptyFieldException.class, ()-> Reserva.instance(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                null,
                LocalDate.of(2026,10,25),
                4,
                "ACTIVA"
        ));
    }

    @Test
    public void InstanceReserva_BlankMail_BlankFieldException(){
        Assertions.assertThrows(BlankFieldException.class, ()-> Reserva.instance(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                "",
                LocalDate.of(2026,10,25),
                4,
                "ACTIVA"
        ));
    }

    @Test
    public void InstanceReserva_ZeroPeople_InvalidGuestCountException(){
        Assertions.assertThrows(InvalidGuestCountException.class, ()-> Reserva.instance(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                "fulanitodetal@gmail.com",
                LocalDate.of(2026,10,25),
                0,
                "ACTIVA"
        ));
    }

    @Test
    public void InstanceReserva_NegativePeople_InvalidGuestCountException(){
        Assertions.assertThrows(InvalidGuestCountException.class, ()-> Reserva.instance(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                "fulanitodetal@gmail.com",
                LocalDate.of(2026,10,25),
                -3,
                "ACTIVA"
        ));
    }

    @Test
    public void InstanceReserva_CancelledNewReservation_CancelledNewInstanceException(){
        Assertions.assertThrows(CancelledNewInstanceException.class, ()-> Reserva.instance(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                "fulanitodetal@gmail.com",
                LocalDate.of(2026,10,25),
                4,
                "CANCELADA"
        ));
    }

    @Test
    public void InstanceReserva_CancelAttemptOnCancelled_AlreadyCancelledException(){
       Reserva reserva = Reserva.instance(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                "fulanitodetal@gmail.com",
                LocalDate.of(2026,10,25),
                4,
                "ACTIVA"
        );
       reserva.cancelar();
       Assertions.assertThrows(AlreadyCancelledException.class,()->reserva.cancelar());
    }

}
