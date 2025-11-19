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
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {
        Falha falha = FalReposi.BuscarPorID(acao.getFalhaId());

        if (falha == null) {
            throw new RuntimeException("Falha não encontrada!");
        }


        acao = repository.registrarConclusaoDeAcao(acao);

        FalReposi.AtualizarResovida(falha.getId());

        if (falha.getCriticidade().equals("CRITICA")) {
            EquRep.AtualizarStatusOperacional(falha.getEquipamentoId());
        }

        return acao;
    }


    }

