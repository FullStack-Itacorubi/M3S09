
package com.example.labschool.repositories;

import com.example.labschool.dtos.PedagogoSeletorDto;
import com.example.labschool.models.PedagogoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedagogoRepository extends JpaRepository<PedagogoModel, UUID>{
    
    @Query(value="SELECT new com.example.labschool.dtos.PedagogoSeletorDto(pd.id, pd.nome)"
            + "FROM com.example.labschool.models.PedagogoModel pd")
    List<PedagogoSeletorDto> findByIdName();
}
