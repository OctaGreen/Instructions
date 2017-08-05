package com.example.demo.repository;

import com.example.demo.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface InstructionRepository extends JpaRepository<Instruction, Long>{
    Instruction findByTitle(String title);
    Instruction findByCategory(String category);
    List<Instruction> findAllByAuthor(String author);
    Instruction findFirstByAuthor(String author);
    Integer getInstructionIdByTitleAndAuthor(String title, String author);
}
