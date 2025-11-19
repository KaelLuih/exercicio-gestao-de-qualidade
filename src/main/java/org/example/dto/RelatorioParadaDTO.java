package org.example.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelatorioParadaDTO {


   private String nome;
   private Long falhaID;
   private BigDecimal tempoParadaHoras;
   private LocalDateTime DataOcorrencia;


    public RelatorioParadaDTO(String nome, Long falhaID, BigDecimal tempoParadaHoras, LocalDateTime dataOcorrencia) {
        this.nome = nome;
        this.falhaID = falhaID;
        this.tempoParadaHoras = tempoParadaHoras;
        DataOcorrencia = dataOcorrencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getFalhaID() {
        return falhaID;
    }

    public void setFalhaID(Long falhaID) {
        this.falhaID = falhaID;
    }

    public BigDecimal getTempoParadaHoras() {
        return tempoParadaHoras;
    }

    public void setTempoParadaHoras(BigDecimal tempoParadaHoras) {
        this.tempoParadaHoras = tempoParadaHoras;
    }

    public LocalDateTime getDataOcorrencia() {
        return DataOcorrencia;
    }

    public void setDataOcorrencia(LocalDateTime dataOcorrencia) {
        DataOcorrencia = dataOcorrencia;
    }
}
