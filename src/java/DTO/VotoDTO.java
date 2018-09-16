package DTO;

/**
 *
 * @author Fernando Chata
 */
public class VotoDTO {
    
    private String candidato;
    private String ciudadano;

    public VotoDTO(String candidato, String ciudadano) {
        this.candidato = candidato;
        this.ciudadano = ciudadano;
    }

    public VotoDTO() {
    }

    public String getCandidato() {
        return candidato;
    }

    public void setCandidato(String candidato) {
        this.candidato = candidato;
    }

    public String getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(String ciudadano) {
        this.ciudadano = ciudadano;
    }

    @Override
    public String toString() {
        return "VotoDTO{" + "candidato=" + candidato + ", ciudadano=" + ciudadano + '}';
    }

    
}
