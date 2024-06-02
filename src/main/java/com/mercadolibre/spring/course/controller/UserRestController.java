package com.mercadolibre.spring.course.controller;

import com.mercadolibre.spring.course.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {
    public Map<String, Object> details=new HashMap<>();

    @GetMapping("/details")
    public Map<String, Object> getDetails(){
        User user=new User();
        user.setName("Juan");
        user.setLastName("Castaño");
        details.put("user", user);
        return details;
    }
}
