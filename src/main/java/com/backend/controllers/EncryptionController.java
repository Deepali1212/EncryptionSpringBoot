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
    public String encrypt(@RequestBody HashMap<String, Object> input) {
        if (!(input.containsKey("message")) || !(input.containsKey("secretKey")) || !(input.containsKey("type"))) {
            return null;
        } else if (input.get("type").toString().equalsIgnoreCase("encrypt"))
            return service.encryptAesGcm(input);
        else return service.decryptAesGcm(input);

    }
    @PostMapping("/getAESEncryption")
    public String getAESEncryption(@RequestBody HashMap<String, Object> input) throws Exception {
        if (!(input.containsKey("message")) || !(input.containsKey("sercretKey")) || !(input.containsKey("type"))) {
            return null;
        }
        return service.getAESEncryption(input);

    }
}
