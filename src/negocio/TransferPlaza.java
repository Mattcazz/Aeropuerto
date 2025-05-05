package negocio;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransferPlaza implements Serializable {
    private static final long serialVersionUID = 1L;

    private int numero;
    private EstadoPlaza estado;
    private String matricula;
    private LocalDateTime llegada;

    public TransferPlaza(int numero, EstadoPlaza estado, String matricula) {
        this.numero = numero;
        this.estado = estado;
        this.matricula = matricula;
        this.llegada = null;
    }

    public int getNumero() { return numero; }
    public EstadoPlaza getEstado() { return estado; }
    public void setEstado(EstadoPlaza estado) { this.estado = estado; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public LocalDateTime getLlegada() { return llegada; }
    public void setLlegada(LocalDateTime llegada) { this.llegada = llegada; }

    @Override
    public String toString() {
        return "Plaza Nº" + numero + " [" + estado + "]" +
               (matricula == null || matricula.isEmpty() ? "" : " - Matrícula: " + matricula);
    }
}