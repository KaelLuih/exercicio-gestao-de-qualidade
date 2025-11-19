package org.example.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FalhaDetalhadaDTO {

    private Long id;

    private String nome;

    private String numeroDeSerie;

    private String areaSetor;

    private String statusOperacional;


    private Long AcaoCorretivaId;



    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    private String responsavel;

    private String descricaoArea;


    private Long falhaId;

    private Long equipamentoId;

    private LocalDateTime dataHoraOcorrencia;

    private String descricao;

    private String criticidade;

    private String status;

    private BigDecimal tempoParadaHoras;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getAreaSetor() {
        return areaSetor;
    }

    public void setAreaSetor(String areaSetor) {
        this.areaSetor = areaSetor;
    }

    public String getStatusOperacional() {
        return statusOperacional;
    }

    public void setStatusOperacional(String statusOperacional) {
        this.statusOperacional = statusOperacional;
    }

    public Long getAcaoCorretivaId() {
        return AcaoCorretivaId;
    }

    public void setAcaoCorretivaId(Long acaoCorretivaId) {
        AcaoCorretivaId = acaoCorretivaId;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getDescricaoArea() {
        return descricaoArea;
    }

    public void setDescricaoArea(String descricaoArea) {
        this.descricaoArea = descricaoArea;
    }

    public Long getFalhaId() {
        return falhaId;
    }

    public void setFalhaId(Long falhaId) {
        this.falhaId = falhaId;
    }

    public Long getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(Long equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public LocalDateTime getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public void setDataHoraOcorrencia(LocalDateTime dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(String criticidade) {
        this.criticidade = criticidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTempoParadaHoras() {
        return tempoParadaHoras;
    }

    public void setTempoParadaHoras(BigDecimal tempoParadaHoras) {
        this.tempoParadaHoras = tempoParadaHoras;
    }

    public FalhaDetalhadaDTO(Long id, String nome, String numeroDeSerie, String areaSetor, String statusOperacional, Long acaoCorretivaId, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String responsavel, String descricaoArea, Long falhaId, Long equipamentoId, LocalDateTime dataHoraOcorrencia, String descricao, String criticidade, String status, BigDecimal tempoParadaHoras) {
        this.id = id;
        this.nome = nome;
        this.numeroDeSerie = numeroDeSerie;
        this.areaSetor = areaSetor;
        this.statusOperacional = statusOperacional;
        AcaoCorretivaId = acaoCorretivaId;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.responsavel = responsavel;
        this.descricaoArea = descricaoArea;
        this.falhaId = falhaId;
        this.equipamentoId = equipamentoId;
        this.dataHoraOcorrencia = dataHoraOcorrencia;
        this.descricao = descricao;
        this.criticidade = criticidade;
        this.status = status;
        this.tempoParadaHoras = tempoParadaHoras;
    }
}
