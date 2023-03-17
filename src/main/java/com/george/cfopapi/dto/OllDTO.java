package com.george.cfopapi.dto;

import com.george.cfopapi.model.AlgorithmModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OllDTO {
    private String caseId;
    private List<String> names;
    private String group;
    private List<AlgorithmModel> algs;
}
