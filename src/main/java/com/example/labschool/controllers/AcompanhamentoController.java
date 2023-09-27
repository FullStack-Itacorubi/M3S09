
package com.example.labschool.controllers;

import com.example.labschool.dtos.AcompanhamentoDto;
import com.example.labschool.dtos.AcompanhamentoPedDto;
import com.example.labschool.models.AcompanhamentoPedagogicoModel;
import com.example.labschool.repositories.AcompanhamentoRepository;
import jakarta.validation.Valid;
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
public class AcompanhamentoController {
    
    @Autowired
    private AcompanhamentoRepository acompanhamentoRepository;
    
   @PostMapping("acompanhamento")
   public ResponseEntity<AcompanhamentoPedagogicoModel> saveAcompanhamento(@RequestBody @Valid AcompanhamentoDto acompanhamentoDto){
        var acompanhamentoModel = new AcompanhamentoPedagogicoModel();
     
        BeanUtils.copyProperties(acompanhamentoDto, acompanhamentoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(acompanhamentoRepository.save(acompanhamentoModel));
   }
   
   @GetMapping("acompanhamentos")
   public ResponseEntity<List<AcompanhamentoPedDto>> getAllAcompanhamentos(){
       return ResponseEntity.status(HttpStatus.OK).body(acompanhamentoRepository.findAllAcompanhamentos());
   }
   
   @PutMapping("acompanhamento/{id}")
   public ResponseEntity<Object>  updateAcompanhamento(@PathVariable(value="id") UUID id, @RequestBody @Valid AcompanhamentoDto acompanhamento){
       Optional<AcompanhamentoPedagogicoModel> acompanhamentoO = acompanhamentoRepository.findById(id);
       if(acompanhamentoO.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acompanhamento Pedagogico not found.");
       }
       
       var acompanhamentoModel = acompanhamentoO.get();
       BeanUtils.copyProperties(acompanhamento, acompanhamentoModel);
       return ResponseEntity.status(HttpStatus.OK).body(acompanhamentoRepository.save(acompanhamentoModel));   
   }
   
    
}
