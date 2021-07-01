package com.backend.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncryptedDataDto {

    private String message;
    private String iv;

}
