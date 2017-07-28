package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.model.Instruction;
import com.example.demo.repository.InstructionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/****Тестовый контроллер. Удалить****/

@RestController
public class DataController {
    @Autowired
    InstructionRepository repository;

    List<Instruction> list = new ArrayList<>();

    @RequestMapping(value="/getinstruction", method=RequestMethod.GET)
    public List<Instruction> getResourse(){
        return list;
    }

    @RequestMapping(value="/postinstruction", method= RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody void postInstruction(@RequestBody Instruction instruct){
        //repository.save(instruct);
        list.add(instruct);
    }
}
/********************************/