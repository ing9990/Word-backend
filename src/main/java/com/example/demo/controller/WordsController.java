package com.example.demo.controller;

import com.example.demo.domain.Words;
import com.example.demo.repository.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WordsController {

    @Autowired
    WordsRepository wordsRepository;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/words")
    public List<Words> findAll(){
        return wordsRepository.findAll();
    }

    @GetMapping("/words/{id}")
    public Optional<Words> findById(@PathVariable Long id) {
        return wordsRepository.findById(id);
    }

    @PostMapping("/words/{word}")
    public ArrayList<Words> addWords(@PathVariable String word){
        wordsRepository.save(new Words(word));
        return (ArrayList<Words>) wordsRepository.findAll();
    }

    @PutMapping("/words/{id}/{word}")
    public List<Words> update(@PathVariable Long id, @PathVariable Words word){
        wordsRepository.findById(id)
                .orElseThrow( () -> new IllegalStateException("id가 없음."))
                .setWord(word.getWord());
        return wordsRepository.findAll();
    }

    @DeleteMapping("/words")
    public void deleteAll(){
        wordsRepository.deleteAll();
    }

    @DeleteMapping("/words/{id}")
    public void deleteById(@PathVariable Long id){
        wordsRepository.deleteById(id);
    }



}
