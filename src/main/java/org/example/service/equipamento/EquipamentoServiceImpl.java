package org.example.service.equipamento;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoServiceImpl implements EquipamentoService{
    EquipamentoRepository repository = new EquipamentoRepository();
    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        return repository.save(equipamento) ;
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        Equipamento equipamentoEncontrado = repository.buscarEquipamentoPorId(id);

        if (repository.buscarEquipamentoPorId(id) == null){
            throw new RuntimeException("Equipamento não encontrado!");
        }

        return equipamentoEncontrado;
    }
}
