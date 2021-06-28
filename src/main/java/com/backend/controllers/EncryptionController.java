package com.backend.controllers;

import com.backend.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
public class EncryptionController {
    @Autowired
    private EncryptionService service;

    @PostMapping("/getEncryptionData")
    public String encrypt(@RequestBody LinkedHashMap<String, Object> input) {
        if (!(input.containsKey("message")) || !(input.containsKey("secretKey")) || !(input.containsKey("type"))) {
            return null;
        } else if (input.get("type").toString().equalsIgnoreCase("encrypt"))
            return service.encryptAesCbc(input);
        else return service.decryptAesCbc(input);

    }
    @PostMapping("/getAESEncryption")
    public String getAESEncryption(@RequestBody LinkedHashMap<String, Object> input) throws Exception {
        if (!(input.containsKey("message")) || !(input.containsKey("secretKey")) || !(input.containsKey("type"))) {
            return null;
        } else if (input.get("type").toString().equalsIgnoreCase("encrypt"))
            return service.encryptAesCbc128(input);
        else return service.decryptAesCbc128(input);

    }
}
