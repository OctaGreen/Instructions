package com.example.demo.controller;

import com.example.demo.model.Instruction;
import com.example.demo.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class InstructionController {
    @Autowired
    InstructionRepository instRepository;

    @GetMapping(value = "/getinstruction/{author:.*}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer, Instruction> getInstructionByAuthor(@PathVariable String author){
        List<Instruction> list = instRepository.findAllByAuthor(author);
        Map<Integer, Instruction> instructionMap = new HashMap<>();
        for(Instruction instruction: list){
            instructionMap.put(instruction.getInstructionId(), instruction);
        }
        return instructionMap;
    }

    @GetMapping(value = "/getinstructionbyid/{instructionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Instruction getInstructionByInstructionId(@PathVariable Integer instructionId){
        System.out.println("INSTRUCTION ID: " + new Integer(instructionId));
        return instRepository.findInstructionByInstructionId(new Integer(instructionId));
    }

    @PostMapping(value = "/createinstruction", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody void createInstruction(@RequestBody Instruction instruction){
        instRepository.save(instruction);
    }

    @PostMapping(value = "/updateinstruction", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody void updateInstruction(@RequestBody Instruction instruction){
        //заменить одну инструкцию другой
    }

}
