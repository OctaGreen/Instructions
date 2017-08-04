package com.example.demo.controller;

import com.example.demo.model.Instruction;
import com.example.demo.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class InstructionController {
    @Autowired
    InstructionRepository instRepository;

    @GetMapping(value = "/getinstruction/{author}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Instruction getInstructionByAuthor(@PathVariable String author){
        return instRepository.findFirstByAuthor("anton@something.com");
    }

   /* @GetMapping("/getinstruction/{title}")
    public Instruction getInstructionByTitle(@PathVariable String title){
        return instRepository.findByTitle(title);
    }

    @GetMapping("/getinstruction/{category}")
    public Instruction getInstructionByCategory(@PathVariable String category){
        return instRepository.findByCategory(category);
    }

    @GetMapping("/getinstruction/{date}")
    public Instruction getInstructionByDate(@PathVariable Date date){
        return instRepository.findByCreationDate(date);
    }
*/
    @PostMapping(value = "/createinstruction", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody void createInstruction(@RequestBody Instruction instruction){
        instRepository.save(instruction);
    }

    @PostMapping(value = "/updateinstruction", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody void updateInstruction(@RequestBody Instruction instruction){
        //заменить одну инструкцию другой
    }

}
