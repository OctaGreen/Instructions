package com.example.demo.controller;

import com.example.demo.model.Instruction;
import com.example.demo.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class InstructionController {
    @Autowired
    InstructionRepository instRepository;

    /*@GetMapping(value = "/getinstruction/{author:.*}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Instruction> getInstructionByAuthor(@PathVariable String author){
        return instRepository.findAllByAuthor(author);
    }*/

    @GetMapping(value = "/getinstruction/{author:.*}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer, Instruction> getInstructionByAuthor(@PathVariable String author){
        List<Instruction> list = instRepository.findAllByAuthor(author);

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
