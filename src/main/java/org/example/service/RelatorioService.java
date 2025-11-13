package org.example.service;

import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.model.Falha;
import org.example.repository.FalhaRepository;
import org.example.repository.RelatorioParadaRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RelatorioService {

    RelatorioParadaRepository repository = new RelatorioParadaRepository();

    FalhaRepository FalRepository = new FalhaRepository();


    public List<RelatorioParadaDTO>gerarRelatorioTempoParada()throws SQLException{
        List<RelatorioParadaDTO> relatorioParadaDTOS = repository.gerarRelatorioTempoParada();
       return  relatorioParadaDTOS;
    }
    public Optional<FalhaDetalhadaDTO>buscarDetalhesCompletosFalha(Long idFalha) throws SQLException {
       List<Falha>falhas = FalRepository.ListarFalhas();
       for(Falha falha: falhas){
           if(falha.getId() != idFalha){
               throw new RuntimeException("Erro id da falha nao é valido");
           }
       }
        return repository.buscarDetalhesCompletosFalha(idFalha);
    }
    public List<EquipamentoContagemFalhasDTO>gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas)throws SQLException{

        List<EquipamentoContagemFalhasDTO> equiDto = repository.gerarRelatorioManutencaoPreventiva(contagemMinimaFalhas);

        if (contagemMinimaFalhas <= 0){
             throw new RuntimeException("ESSA MAQUINA NAO TEVE FALHAS CADASTRADAS");
         }
        return equiDto;
    }



}
