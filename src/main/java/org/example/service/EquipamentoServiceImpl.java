package org.example.service;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoServiceImpl implements EquipamentoService{

    EquipamentoRepository repository = new EquipamentoRepository();
   

   

    
    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        return repository.save(equipamento);
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        List<Equipamento> equipamentos = repository.buscarEquipamentoPorId(id);

        return equipamentos;
    }
}
