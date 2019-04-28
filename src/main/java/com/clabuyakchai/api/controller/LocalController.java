package com.clabuyakchai.api.controller;

import com.clabuyakchai.api.model.Local;
import com.clabuyakchai.api.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/local")
@RestController
public class LocalController {

    private LocalService localService;

    @Autowired
    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping(value = "/all")
    public List<Local> getAll(){
        return localService.getAll();
    }
}
