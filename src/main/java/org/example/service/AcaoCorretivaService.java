package org.example.service;

import org.example.model.AcaoCorretiva;
import org.example.model.Falha;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.util.List;

public class AcaoCorretivaService {
    AcaoCorretivaRepository repository = new AcaoCorretivaRepository();
    FalhaRepository FalReposi = new FalhaRepository();
    EquipamentoRepository EquRep = new EquipamentoRepository();
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao)throws SQLException{
        List<Falha>falhas = FalReposi.buscarFalhasCriticasAbertas();

        for(Falha falha: falhas){
            if(falha.getId() == acao.getFalhaId()){
                EquRep.AtualizarStatusOperacional(acao.getFalhaId());
            }
        }
        return repository.registrarConclusaoDeAcao(acao);
    }




}
