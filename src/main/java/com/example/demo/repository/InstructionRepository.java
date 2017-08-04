package com.example.demo.repository;

import com.example.demo.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface InstructionRepository extends JpaRepository<Instruction, Long>{
    Instruction findByTitle(String title);
    Instruction findByCategory(String category);
    Instruction findByCreationDate(Date creationDate);
    Instruction findAllByAuthor(String author);
    Instruction findFirstByAuthor(String author);
}
