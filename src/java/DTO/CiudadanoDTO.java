package DTO;

/**
 *
 * @author Fernando Chata
 */
public class CiudadanoDTO {
    
    private String rut;
    private String nombre;

    public CiudadanoDTO(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }

    public CiudadanoDTO() {
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CandidatoDTO{" + "rut=" + rut + ", nombre=" + nombre + '}';
    }

    
}
