package com.backend.controllers;

import com.backend.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class EncryptionController {
    @Autowired
    private EncryptionService service;

    @PostMapping("/getEncryptionData")
    public Object encrypt(@RequestBody HashMap<String, String> input) {
        if (!(input.containsKey("message")) || !(input.containsKey("sercretKey"))) {
            return null;
        }
        return service.encrypt(input.get("message"), input.get("sercretKey"));
    }
}
