package DTO;

/**
 *
 * @author Fernando
 */
public class ConteoDTO {
    
    private String candidato;
    private int conteo;

    public ConteoDTO() {
    }

    public ConteoDTO(String candidato, int conteo) {
        this.candidato = candidato;
        this.conteo = conteo;
    }

    public String getCandidato() {
        return candidato;
    }

    public void setCandidato(String candidato) {
        this.candidato = candidato;
    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    @Override
    public String toString() {
        return "ConteoDTO{" + "candidato=" + candidato + ", conteo=" + conteo + '}';
    }
    
    
    
}
