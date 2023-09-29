package com.example.labschool.repositories;

import com.example.labschool.dtos.AlunoSeletorDto;
import com.example.labschool.models.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlunoRepository  extends JpaRepository<AlunoModel, UUID>{
    
    @Query(value="SELECT new com.example.labschool.dtos.AlunoSeletorDto(al.id, al.nome)"
        + " FROM AlunoModel al")
    List<AlunoSeletorDto> findByIdName();

    List<AlunoModel> findByNomeContainingIgnoreCase(String nome);
}
