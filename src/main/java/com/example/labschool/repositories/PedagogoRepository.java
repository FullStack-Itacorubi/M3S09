
package com.example.labschool.repositories;

import com.example.labschool.dtos.PedagogoSeletorDto;
import com.example.labschool.models.PedagogoModel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedagogoRepository extends JpaRepository<PedagogoModel, UUID>{
    
    @Query(value="SELECT new com.example.labschool.dtos.PedagogoSeletorDto(pd.id, pd.nome)"
            + "FROM com.example.labschool.models.PedagogoModel pd")
    List<PedagogoSeletorDto> findByIdName();
}
