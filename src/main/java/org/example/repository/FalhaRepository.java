package org.example.repository;

import org.example.model.Falha;
import org.example.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FalhaRepository {
    public Falha save(Falha falha) throws SQLException {
        String query = """
                INSERT INTO Falha 
                (equipamentoId,dataHoraOcorrencia,descricao,criticidade,status,tempoParadaHoras) 
                VALUES 
                (?,?,?,?,?,?);
                """;
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, falha.getEquipamentoId());
            stmt.setTimestamp(2, Timestamp.valueOf(falha.getDataHoraOcorrencia()));
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, falha.getStatus());
            stmt.setBigDecimal(6, falha.getTempoParadaHoras());
            stmt.executeUpdate();


        }

        return falha;
    }

    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        List<Falha>falhas = new ArrayList<>();

        String query = """
            SELECT id, equipamentoId,dataHoraOcorrencia,descricao,criticidade,status,tempoParadaHoras
            FROM Falha
            WHERE status = 'ABERTA' AND criticidade = 'CRITICA'
            """;
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){

            }
            falhas.add( new Falha(
                    rs.getLong("id"),
                    rs.getLong("equipamentoId"),
                    rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime(),
                    rs.getString("descricao"),
                    rs.getString("criticidade"),
                    rs.getString("status"),
                    rs.getBigDecimal("tempoParadaHoras")
            ));
        }
        return falhas;
    }
    public List<Falha> ListarFalhas() throws SQLException {
        List<Falha>falhas = new ArrayList<>();

        String query = """
            SELECT id, equipamentoId,dataHoraOcorrencia,descricao,criticidade,status,tempoParadaHoras
            FROM Falha
            """;
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){

            }
            falhas.add( new Falha(
                    rs.getLong("id"),
                    rs.getLong("equipamentoId"),
                    rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime(),
                    rs.getString("descricao"),
                    rs.getString("criticidade"),
                    rs.getString("status"),
                    rs.getBigDecimal("tempoParadaHoras")
            ));
        }
        return falhas;
    }
}
