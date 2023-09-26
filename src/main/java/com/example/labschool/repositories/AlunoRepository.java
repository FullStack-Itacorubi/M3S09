package com.example.labschool.repositories;

import com.example.labschool.models.AlunoModel;
import com.example.labschool.dtos.AlunoSeletorDto;
import java.util.UUID;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository  extends JpaRepository<AlunoModel, UUID>{
    
    @Query(value="SELECT new com.example.labschool.dtos.AlunoSeletorDto(al.id, al.nome)"
        + " FROM AlunoModel al")
    List<AlunoSeletorDto> findByIdName();

}
