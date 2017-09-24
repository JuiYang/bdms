package edu.xidian.sselab.bdms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name + "!";
    }
    
}
