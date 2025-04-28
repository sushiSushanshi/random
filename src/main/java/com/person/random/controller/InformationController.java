package com.person.random.controller;


import com.person.random.model.PersonInfo;
import com.person.random.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/person")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping
    public Mono<PersonInfo> getPerson() {
        return informationService.generatePerson();
    }
}
