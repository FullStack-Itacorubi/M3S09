package com.example.labschool.controllers;


import com.example.labschool.dtos.PedagogoDto;
import com.example.labschool.dtos.PedagogoSeletorDto;
import com.example.labschool.models.PedagogoModel;
import com.example.labschool.repositories.PedagogoRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class PedagogoController {
    
    @Autowired
    private PedagogoRepository pedagogoRepository;
    
    private static Logger LOGGER = LoggerFactory.getLogger(PedagogoController.class);
    
    @PostMapping("pedagogo")
    public ResponseEntity<PedagogoModel> savePedagogo(@RequestBody @Valid PedagogoDto pedagogoDto){
        var pedagogoModel = new PedagogoModel();
        
        BeanUtils.copyProperties(pedagogoDto, pedagogoModel); 
        LOGGER.info("Instância Pedagogo cadastrada: " + pedagogoDto.nome());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedagogoRepository.save(pedagogoModel));
    }
    
    @GetMapping("pedagogos")
    public ResponseEntity<List<PedagogoDto>> getAllPedagogos(){
        LOGGER.info("Listagem Pedagogos Concluída.");
        return ResponseEntity.status(HttpStatus.OK).body(pedagogoRepository.findAll()
                .stream().map(PedagogoDto::new).toList());
    }
    
    @GetMapping("pedagogos/{id}")
    public ResponseEntity<Object> getOnePedagogo(@PathVariable(value="id") UUID id){
        Optional<PedagogoModel> pedagogoO = pedagogoRepository.findById(id);
        
        if(pedagogoO.isEmpty()){
            LOGGER.info("Pedagogo não foi identificado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedagogo not found.");
        }
        
        Optional<PedagogoDto> pedagogoDto = pedagogoO.map(PedagogoDto::new);
        LOGGER.info("Consulta Pedagogo concluída");
        return ResponseEntity.status(HttpStatus.OK).body(pedagogoDto.get());  
    }
    
    @PutMapping("pedagogo/{id}")
    public ResponseEntity<Object> updatePedagogo(@PathVariable(value="id") UUID id,
                                                     @RequestBody @Valid PedagogoDto pedagogoDto){
        Optional<PedagogoModel> pedagogoO = pedagogoRepository.findById(id);
        if(pedagogoO.isEmpty()){
            LOGGER.info("ID [" + id + "] não identificado na base de dados" );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedagogo not found.");
        }
        
       var pedagogoModel = pedagogoO.get();
       BeanUtils.copyProperties(pedagogoDto, pedagogoModel);
       LOGGER.info("Pedagogo de ID["+id+"] atualizado na base de dados");
       return ResponseEntity.status(HttpStatus.OK).body(pedagogoRepository.save(pedagogoModel));
    }

    @DeleteMapping("pedagogo/{id}")
    public ResponseEntity<Object> deletePedagogo(@PathVariable(value="id") UUID id){
        Optional<PedagogoModel> pedagogoO = pedagogoRepository.findById(id);
        if(pedagogoO.isEmpty()){
            LOGGER.info("ID [" + id + "] não identificado na base de dados" );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedagogo não encontrado!.");
        }
        
        pedagogoRepository.deleteById(id);
        LOGGER.info("Pedagogo de ID["+id+"] excluído da base de dados");
        return ResponseEntity.status(HttpStatus.OK).body("Pedagogo deleted succesfully.");    
    }
    
    @GetMapping("pedagogos/name")
    public ResponseEntity<List<PedagogoSeletorDto>> getPedagogo(){
        LOGGER.info("Listagem de Pedagogos por ID e NOME concluída");
        return  ResponseEntity.status(HttpStatus.OK).body(pedagogoRepository.findByIdName());
    }
}
