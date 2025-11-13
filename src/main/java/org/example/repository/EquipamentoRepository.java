package org.example.repository;

import org.example.model.Equipamento;
import org.example.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepository {
    public Equipamento save(Equipamento equipamento) throws SQLException {
        String query = """
                INSERT INTO Equipamento 
                (nome,numeroDeSerie,areaSetor,statusOperacional)
                VALUES (?,?,?,?);
                """;
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, "OPERACIONAL");
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                equipamento.setId(rs.getLong(1));
            }
        }
        return equipamento;
    }


    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        String query = """
                SELECT
                 id,nome,numeroDeSerie,areaSetor,statusOperacional
                FROM Equipamento
                WHERE id = ?;
                """;
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Equipamento(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("numeroDeSerie"),
                        rs.getString("areaSetor"),
                        rs.getString("statusOperacional")
                );
            }
        }

        return null;
    }
    public boolean AtualizarStatus(Long id)throws SQLException{
        String query = """
                
                UPDATE Equipamento e
                JOIN Falha f ON f.equipamentoId = e.id
                SET e.status = 'EM_MANUTENCAO'
                WHERE e.id = ? 
                AND
                f.criticidade = 'CRITICO'""";
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
        return true;
    }
    public void AtualizarStatusOperacional(Long id )throws SQLException{
        String query = """
                UPDATE Equipamento e
                JOIN Falha f ON f.equipamentoId = e.id
                SET e.statusOperacional = 'EM_MANUTENCAO'
                WHERE e.id = ?                        
                AND f.criticidade = 'CRITICA'""";
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }
    public List<Equipamento>buscarEquipamentosSemFalhasPorPeriodo()throws SQLException{
        List<Equipamento>equipamentos = new ArrayList<>();
        String query = """
                SELECT
                    e.nome,
                    COUNT(f.id) AS totalFalhas
                FROM Equipamento e
                LEFT JOIN Falha f
                    ON f.equipamentoId = e.id
                    AND f.dataHoraOcorrencia BETWEEN ? AND ?
                GROUP BY e.id, e.nome
                HAVING totalFalhas = 0;  
                """;
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                 equipamentos.add(new Equipamento(
                         rs.getLong("id"),
                         rs.getString("nome"),
                         rs.getString("numeroDeSerie"),
                         rs.getString("areaSetor"),
                         rs.getString("statusOperacional")
                 ));
            }
        }
        return equipamentos;
    }
}
