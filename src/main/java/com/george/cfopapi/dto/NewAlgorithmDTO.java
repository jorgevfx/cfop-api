package com.george.cfopapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewAlgorithmDTO {
    @NotNull
    @NotBlank
    private String oldAlg;
    @NotNull
    @NotBlank
    private String newAlg;
}

