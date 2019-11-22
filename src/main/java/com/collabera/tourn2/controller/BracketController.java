package com.collabera.tourn2.controller;

import com.collabera.tourn2.model.Bracket;
import com.collabera.tourn2.repository.BracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BracketController {

    @Autowired
    private BracketRepository bracketRepository;

    @PostMapping("/addBracket")
    public String saveBracket(@RequestBody Bracket bracket)
    {
        bracketRepository.save(bracket);

        return "Added bracket with id: " + bracket.getId();
    }

    @GetMapping("/findAllBooks/{id}")
    public Optional<Bracket> getBracket(@PathVariable String id)
    {
        return bracketRepository.findById(id);
    }
}
