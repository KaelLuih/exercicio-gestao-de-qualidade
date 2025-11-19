package org.example.repository;

import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RelatorioParadaRepository {

    public List<RelatorioParadaDTO> gerarRelatorioTempoParada() throws SQLException {
        List<RelatorioParadaDTO> relatorioParadaDTOS = new ArrayList<>();

        String query = """
                SELECT e.nome, f.id, f.dataHoraOcorrencia  , f.tempoParadaHoras
                from Equipamento e
                JOIN Falha f ON f.equipamentoID = e.id;
                """;
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioParadaDTOS.add(new RelatorioParadaDTO(
                        rs.getString("nome"),
                        rs.getLong("id"),
                        rs.getBigDecimal("tempoParadaHoras"),
                        rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime()

                ));
            }


        }
        return relatorioParadaDTOS;


    }

    public Optional<FalhaDetalhadaDTO> buscarDetalhesCompletosFalha(Long idFalha) throws SQLException {
        String query = """
                    SELECT
                        e.id AS equipamentoId,
                        e.nome AS nome,
                        e.numeroDeSerie,
                        e.areaSetor,
                        e.statusOperacional,
                        a.id AS acaoCorretivaId,
                        a.dataHoraInicio,
                        a.dataHoraFim,
                        a.responsavel,
                        a.descricaoAcao AS descricaoArea,
                        f.id AS falhaId,
                        f.equipamentoId,
                        f.dataHoraOcorrencia,
                        f.descricao,
                        f.criticidade,
                        f.status,
                        f.tempoParadaHoras
                    FROM Falha f
                    JOIN Equipamento e ON e.id = f.equipamentoId
                    LEFT JOIN AcaoCorretiva a ON a.falhaId = f.id
                    WHERE f.id = ?
                """;

        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, idFalha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Timestamp inicio = rs.getTimestamp("dataHoraInicio");
                    Timestamp fim = rs.getTimestamp("dataHoraFim");
                    Timestamp ocorrencia = rs.getTimestamp("dataHoraOcorrencia");

                    FalhaDetalhadaDTO dto = new FalhaDetalhadaDTO(
                            rs.getLong("equipamentoId"),
                            rs.getString("nome"),
                            rs.getString("numeroDeSerie"),
                            rs.getString("areaSetor"),
                            rs.getString("statusOperacional"),
                            rs.getLong("acaoCorretivaId"),
                            (inicio != null) ? inicio.toLocalDateTime() : null,
                            (fim != null) ? fim.toLocalDateTime() : null,
                            rs.getString("responsavel"),
                            rs.getString("descricaoArea"),
                            rs.getLong("falhaId"),
                            rs.getLong("equipamentoId"),
                            (ocorrencia != null) ? ocorrencia.toLocalDateTime() : null,
                            rs.getString("descricao"),
                            rs.getString("criticidade"),
                            rs.getString("status"),
                            rs.getBigDecimal("tempoParadaHoras")
                    );

                    return Optional.of(dto);
                }
            }
        }

        return Optional.empty();
    }
    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas)throws SQLException{
        List<EquipamentoContagemFalhasDTO>equipamentoContagemFalhasDTOS = new ArrayList<>();
        String query = """              
                SELECT e.id,e.nome,e.numeroDeSerie,e.areaSetor,e.statusOperacional,
                COUNT(f.equipamentoId) as numerodeVezQueFalhou
                FROM Equipamento e
                JOIN Falha f ON f.equipamentoId = e.id
                GROUP BY e.id,e.nome,e.numeroDeSerie,e.areaSetor,e.statusOperacional
                HAVING COUNT(f.equipamentoId ) >= ?
                order by numerodeVezQueFalhou desc
                """;
        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1,contagemMinimaFalhas);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
            equipamentoContagemFalhasDTOS.add(new EquipamentoContagemFalhasDTO(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getString("numeroDeSerie"),
                    rs.getString("areaSetor"),
                    rs.getString("statusOperacional")
            ));
            }
        }

return equipamentoContagemFalhasDTOS;
        }

}








