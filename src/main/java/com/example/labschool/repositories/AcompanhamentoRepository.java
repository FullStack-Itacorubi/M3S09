package com.example.labschool.repositories;

import com.example.labschool.dtos.AcompanhamentoPedDto;
import com.example.labschool.models.AcompanhamentoPedagogicoModel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AcompanhamentoRepository extends JpaRepository<AcompanhamentoPedagogicoModel, UUID>{
    
    @Query(value="SELECT new com.example.labschool.dtos.AcompanhamentoPedDto(ac.id, ac.titulo, al.nome, pd.nome, ac.dataAcompanhamento, ac.finalizado) "
            + "FROM com.example.labschool.models.AcompanhamentoPedagogicoModel ac "
            + "INNER JOIN ac.aluno al "
            + "INNER JOIN ac.pedagogo pd")
    List<AcompanhamentoPedDto> findAllAcompanhamentos();
    
    
}
