package com.agh.communicationplatform;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class HealthCheckController {

    @GetMapping
    public String index() {
        return "Communication platform";
    }
}

