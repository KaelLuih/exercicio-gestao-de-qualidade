package org.example.service;

import org.example.model.Equipamento;
import org.example.model.Falha;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.util.List;

public class FalhaService {
    FalhaRepository repository = new FalhaRepository();
    EquipamentoRepository EquRepository = new EquipamentoRepository();

    public Falha registrarNovaFalha(Falha falha) throws SQLException {
        Equipamento verificacaoExistenciaID = EquRepository.buscarEquipamentoPorId(falha.getEquipamentoId());
        if (verificacaoExistenciaID == null) {
            throw new RuntimeException("O id Do Equipamento nao existe");
        }
        if (falha.getCriticidade().equals("CRITICO")) {

            EquRepository.AtualizarStatus(falha.getEquipamentoId());
        }
        return repository.save(falha);

    }
    public List<Falha> buscarFalhasCriticasAbertas()throws SQLException{
        return repository.buscarFalhasCriticasAbertas();
    }
}
