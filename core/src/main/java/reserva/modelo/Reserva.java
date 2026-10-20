package reserva.modelo;

import reserva.exception.*;

import java.time.LocalDate;
import java.util.UUID;

public class Reserva {
    private UUID id;
    private String emailContacto;
    private LocalDate fecha;
    private int cantidadPersonas;
    private String estado;

    private Reserva(UUID id, String emailContacto, LocalDate fecha, int cantidadPersonas, String estado) {
        this.id = id;
        this.emailContacto = emailContacto;
        this.fecha = fecha;
        this.cantidadPersonas = cantidadPersonas;
        this.estado = estado;
    }

    public static Reserva instance(UUID id, String emailContacto, LocalDate fecha, int cantidadPersonas, String estado) {
        if(id==null){
            throw new EmptyFieldException("El identificador no puede ser nulo");
        }
        if(fecha==null){
            throw new EmptyFieldException("La fecha no puede estar vacía");
        }
        if(emailContacto==null){
            throw new EmptyFieldException("El mail no puede estar vacío");
        }else if(emailContacto.isBlank()){
            throw new BlankFieldException("El mail no puede estar en blanco");
        }
        if(cantidadPersonas<=0) {
            throw new InvalidGuestCountException("La cantidad de personas en la reserva debe ser positiva");
        }
        if(!estado.equals("ACTIVA")) {
            throw new CancelledNewInstanceException("Una reserva nueva comienza en estado \"ACTIVA\"");
        }
        return new Reserva(id,emailContacto,fecha,cantidadPersonas,estado);

    };

    public void cancelar() throws AlreadyCancelledException{
        if(this.estado.equals("ACTIVA")) {
            this.estado = "CANCELADA";
        }else{
            throw new AlreadyCancelledException("La reserva que se quiere cancelar ya está cancelada");
        }
        return;
    }
}
