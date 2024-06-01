package com.mercadolibre.rampup_juan_castano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mercadolibre.rampup_juan_castano.services.ManageFruitService;

@RestController
@RequestMapping("/fruits")
public class ManageFruitController {

    @Autowired
    ManageFruitService manageFruitsBean;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> getFruitById(@PathVariable String id) throws Exception {
        return manageFruitsBean.getFruitById(id);
    }
}
