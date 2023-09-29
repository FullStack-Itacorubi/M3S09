package com.example.labschool.controllers;

import com.example.labschool.dtos.AlunoDto;
import com.example.labschool.dtos.AlunoSeletorDto;
import com.example.labschool.services.AlunoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<?> saveAluno(@RequestBody @Valid AlunoDto alunoDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(alunoDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAlunos() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneAluno(@PathVariable(value = "id") UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alunoService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAluno(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid AlunoDto alunoDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alunoService.save(id, alunoDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable(value = "id") UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alunoService.deleteById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("name")
    public ResponseEntity<?> getAluno() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAllIdNome());
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<?> getAlunoByNome(@PathVariable String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findByNome(nome));
    }
}
